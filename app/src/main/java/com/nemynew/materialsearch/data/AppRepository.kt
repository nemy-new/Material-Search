package com.nemynew.materialsearch.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.Normalizer
import java.util.Locale
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

data class AppInfo(
    val label: String,
    val packageName: String,
    val componentName: String, // Unique identifier for the activity
    val intent: Intent
)

class AppRepository(private val context: Context) {

    private data class CachedApp(
        val appInfo: AppInfo,
        val normalizedLabel: String,
        val kanaLabel: String,
        val associatedShortcuts: List<String>
    )

    private var cachedApps: List<CachedApp> = emptyList()

    private val cacheFile = File(context.filesDir, "apps_cache.json")

    suspend fun loadApps() {
        withContext(Dispatchers.IO) {
            // 1. Load from cache first
            val cachedData = loadCache()
            
            // Prepare normalized static shortcuts ONCE
            val normalizedStaticShortcuts = ShortcutData.staticShortcuts.mapValues { (_, targets) ->
                targets.map { normalize(it) }
            }

            // Function to compute shortcuts for an app
            fun computeShortcutsForApp(normalizedLabel: String): List<String> {
                return normalizedStaticShortcuts.filterValues { normalizedTargets ->
                    normalizedTargets.any { target -> normalizedLabel.contains(target, ignoreCase = true) }
                }.keys.map { normalize(it) }
            }

            var updatedList = if (cachedData.isNotEmpty()) {
                // RE-COMPUTE shortcuts for cached apps to ensure new static shortcuts are applied
                cachedData.map { cachedApp ->
                    val shortcuts = computeShortcutsForApp(cachedApp.normalizedLabel)
                    cachedApp.copy(associatedShortcuts = shortcuts)
                }.toMutableList()
            } else {
                mutableListOf()
            }

            // 2. Sync with PackageManager
            val pm = context.packageManager
            val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
            val resolvedInfos = pm.queryIntentActivities(mainIntent, 0)
            
            val currentApps = resolvedInfos.mapNotNull { resolveInfo ->
                val packageName = resolveInfo.activityInfo.packageName
                val componentName = resolveInfo.activityInfo.name
                if (packageName == context.packageName) null else packageName to componentName
            }

            val cachedKeys = updatedList.map { it.appInfo.packageName to it.appInfo.componentName }.toSet()
            val currentKeys = currentApps.toSet()

            val addedKeys = currentKeys - cachedKeys
            val removedKeys = cachedKeys - currentKeys

            // Remove uninstalled apps matches
            if (removedKeys.isNotEmpty()) {
                updatedList.removeAll { 
                    (it.appInfo.packageName to it.appInfo.componentName) in removedKeys 
                }
            }

            // Add new apps
            if (addedKeys.isNotEmpty()) {
                val newApps = resolvedInfos.mapNotNull { resolveInfo ->
                    val packageName = resolveInfo.activityInfo.packageName
                    val componentName = resolveInfo.activityInfo.name
                    if ((packageName to componentName) in addedKeys) {
                        try {
                            val label = resolveInfo.loadLabel(pm).toString()
                            val intent = pm.getLaunchIntentForPackage(packageName)?.apply {
                                setClassName(packageName, componentName)
                            }
                            if (intent != null) {
                                val appInfo = AppInfo(label, packageName, componentName, intent)
                                val normalizedLabel = normalize(label)
                                val kanaLabel = getKanaFromRomaji(normalizedLabel)
                                val associatedShortcuts = computeShortcutsForApp(normalizedLabel)
                                CachedApp(appInfo, normalizedLabel, kanaLabel, associatedShortcuts)
                            } else null
                        } catch (e: Exception) { null }
                    } else null
                }
                updatedList.addAll(newApps)
            }

            cachedApps = updatedList.sortedBy { it.appInfo.label }
            saveCache(cachedApps)
        }
    }

    private fun saveCache(apps: List<CachedApp>) {
        try {
            val root = JSONArray()
            apps.forEach { app ->
                val obj = JSONObject().apply {
                    put("label", app.appInfo.label)
                    put("packageName", app.appInfo.packageName)
                    put("componentName", app.appInfo.componentName)
                    put("normalizedLabel", app.normalizedLabel)
                    put("kanaLabel", app.kanaLabel)
                    put("shortcuts", JSONArray(app.associatedShortcuts))
                }
                root.put(obj)
            }
            cacheFile.writeText(root.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadCache(): List<CachedApp> {
        if (!cacheFile.exists()) return emptyList()
        return try {
            val content = cacheFile.readText()
            val root = JSONArray(content)
            val list = mutableListOf<CachedApp>()
            for (i in 0 until root.length()) {
                val obj = root.getJSONObject(i)
                val label = obj.getString("label")
                val packageName = obj.getString("packageName")
                val componentName = obj.getString("componentName")
                val normalizedLabel = obj.getString("normalizedLabel")
                val kanaLabel = obj.getString("kanaLabel")
                val shortcutsArray = obj.getJSONArray("shortcuts")
                val shortcuts = mutableListOf<String>()
                for (j in 0 until shortcutsArray.length()) {
                    shortcuts.add(shortcutsArray.getString(j))
                }
                
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_LAUNCHER)
                    setClassName(packageName, componentName)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                }
                
                list.add(CachedApp(
                    AppInfo(label, packageName, componentName, intent),
                    normalizedLabel,
                    kanaLabel,
                    shortcuts
                ))
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Basic normalization for Japanese text (half-width/full-width) and Hiragana -> Katakana
    private fun normalize(input: String): String {
        // Normalize Full-width/Half-width
        var text = Normalizer.normalize(input, Normalizer.Form.NFKC)
        // Convert Hiragana to Katakana for broader matching
        text = text.map { char ->
            if (char in '\u3041'..'\u3096') {
                (char.code + 0x60).toChar()
            } else {
                char
            }
        }.joinToString("")
        return text.lowercase(Locale.getDefault())
    }

    private fun getRomajiFromKana(japanese: String): String {
        var result = normalize(japanese)
        val kanaToRomaji = mapOf(
            "あ" to "a", "い" to "i", "う" to "u", "え" to "e", "お" to "o",
            "か" to "ka", "き" to "ki", "く" to "ku", "け" to "ke", "こ" to "ko",
            "さ" to "sa", "し" to "shi", "す" to "su", "せ" to "se", "そ" to "so",
            "た" to "ta", "ち" to "chi", "つ" to "tsu", "て" to "te", "と" to "to",
            "な" to "na", "に" to "ni", "ぬ" to "nu", "ね" to "ne", "の" to "no",
            "は" to "ha", "ひ" to "hi", "ふ" to "fu", "へ" to "he", "ほ" to "ho",
            "ま" to "ma", "み" to "mi", "む" to "mu", "め" to "me", "も" to "mo",
            "や" to "ya", "ゆ" to "yu", "よ" to "yo",
            "ら" to "ra", "り" to "ri", "る" to "ru", "れ" to "re", "ろ" to "ro",
            "わ" to "wa", "を" to "o", "ん" to "n",
            "が" to "ga", "ぎ" to "gi", "ぐ" to "gu", "げ" to "ge", "ご" to "go",
            "ざ" to "za", "じ" to "ji", "ず" to "zu", "ぜ" to "ze", "ぞ" to "zo",
            "だ" to "da", "ぢ" to "ji", "づ" to "zu", "で" to "de", "ど" to "do",
            "ば" to "ba", "び" to "bi", "ぶ" to "bu", "べ" to "be", "ぼ" to "bo",
            "ぱ" to "pa", "ぴ" to "pi", "ぷ" to "pu", "ぺ" to "pe", "ぽ" to "po",
            "きゃ" to "kya", "きゅ" to "kyu", "きょ" to "kyo",
            "しゃ" to "sha", "しゅ" to "shu", "しょ" to "sho",
            "ちゃ" to "cha", "ちゅ" to "chu", "ちょ" to "cho",
            "にゃ" to "nya", "にゅ" to "nyu", "にょ" to "nyo",
            "ひゃ" to "hya", "ひゅ" to "hyu", "ひょ" to "hyo",
            "みゃ" to "mya", "みゅ" to "myu", "みょ" to "myo",
            "りゃ" to "rya", "りゅ" to "ryu", "りょ" to "ryo",
            "ぎゃ" to "gya", "ぎゅ" to "gyu", "ぎょ" to "gyo",
            "じゃ" to "ja", "じゅ" to "ju", "じょ" to "jo",
            "びゃ" to "bya", "びゅ" to "byu", "びょ" to "byo",
            "ぴゃ" to "pya", "ぴゅ" to "pyu", "ぴょ" to "pyo"
        )
        
        // Convert to Hiragana first for consistent matching
        var hira = result.map { char ->
            if (char in '\u30A1'..'\u30F6') {
                (char.code - 0x60).toChar()
            } else {
                char
            }
        }.joinToString("")

        // Long vowel processing
        hira = hira.replace("ー", "")

        var romaji = hira
        // Sort keys by length DESC to match longer ones (like "きゃ") first
        kanaToRomaji.keys.sortedByDescending { it.length }.forEach { kana ->
            romaji = romaji.replace(kana, kanaToRomaji[kana]!!)
        }
        
        return romaji
    }

    private fun getKanaFromRomaji(romaji: String): String {
        var result = romaji.lowercase(Locale.ROOT)
        // Simple and limited mapping for common use cases. 
        // A full IME implementation is too complex for this snippet, but this covers basic matching.
        // We replace longer sequences first.
        val replacements = mapOf(
            "kamera" to "カメラ", "camera" to "カメラ",
            "set" to "セッ", "settei" to "設定", // Special case for common "Settings"
            "insta" to "インスタ",
            "line" to "ライン",
            "mail" to "メール",
            "crome" to "クローム", "chrome" to "クローム",
            "google" to "グーグル",
            "map" to "マップ",
            "youtube" to "ユーチューブ",
            "store" to "ストア",
            "play" to "プレイ",
            "music" to "ミュージック",
            "denwa" to "電話", "phone" to "電話",
            "calc" to "電卓",
            "photo" to "フォト",
            "clock" to "時計",
            "calendar" to "カレンダー",
            "browser" to "ブラウザ",
            "game" to "ゲーム"
        )
        
        // Check for direct matches or prefix matches in our limited dictionary
        replacements.forEach { (k, v) ->
             if (result.contains(k)) {
                 result = result.replace(k, v)
             }
        }
        
        return result
    }

    suspend fun searchApps(query: String, userShortcuts: Map<String, String> = emptyMap()): List<AppInfo> {
        if (cachedApps.isEmpty()) {
            loadApps()
        }
        
        if (query.isBlank()) {
            return cachedApps.map { it.appInfo }
        }

        val normalizedQuery = normalize(query)
        val kanaQuery = getKanaFromRomaji(normalizedQuery)
        val romajiQuery = getRomajiFromKana(normalizedQuery)
        
        return withContext(Dispatchers.Default) {
            cachedApps.mapNotNull { cachedApp ->
                var score = 0
                
                // 1. User Shortcuts Check (Manual overrides - HIGHEST PRIORITY)
                if (userShortcuts.isNotEmpty()) {
                    val target = userShortcuts[query.lowercase()] ?: userShortcuts[normalizedQuery]
                    if (target != null) {
                        val (pkg, cls) = target.split("|")
                        if (cachedApp.appInfo.packageName == pkg && cachedApp.appInfo.componentName == cls) {
                            score = 1000 // Ultimate match
                        }
                    } else {
                        // Prefix match for user shortcuts
                        val match = userShortcuts.entries.find { (key, _) ->
                             key.startsWith(query.lowercase()) ||
                             key.startsWith(normalizedQuery) ||
                             key.startsWith(romajiQuery) 
                        }
                        if (match != null) {
                            val (pkg, cls) = match.value.split("|")
                            if (cachedApp.appInfo.packageName == pkg && cachedApp.appInfo.componentName == cls) {
                                score = 800 // High priority shortcut prefix
                            }
                        }
                    }
                }

                // Skip if already found via shortcut with high score
                if (score < 800) {
                    // 2. Exact matches
                    if (cachedApp.normalizedLabel == normalizedQuery) {
                        score = 500
                    } else if (cachedApp.normalizedLabel == kanaQuery || cachedApp.normalizedLabel == romajiQuery) {
                        score = 450
                    }
                    // 3. Static Shortcuts matches
                    else if (cachedApp.associatedShortcuts.any { it == normalizedQuery || it == romajiQuery }) {
                        score = 400
                    }
                    // 4. Prefix matches
                    else if (cachedApp.normalizedLabel.startsWith(normalizedQuery, ignoreCase = true)) {
                        score = 300
                    } else if (cachedApp.normalizedLabel.startsWith(kanaQuery, ignoreCase = true) || 
                               cachedApp.normalizedLabel.startsWith(romajiQuery, ignoreCase = true)) {
                        score = 250
                    }
                    // 5. Static Shortcut prefix matches
                    else if (cachedApp.associatedShortcuts.any { it.startsWith(normalizedQuery) || it.startsWith(romajiQuery) }) {
                        score = 200
                    }
                    // 6. Contains matches (Lowest relevance)
                    else if (cachedApp.normalizedLabel.contains(normalizedQuery, ignoreCase = true)) {
                        score = 100
                    } else if (cachedApp.normalizedLabel.contains(kanaQuery, ignoreCase = true) || 
                               cachedApp.normalizedLabel.contains(romajiQuery, ignoreCase = true)) {
                        score = 80
                    }
                }

                if (score > 0) {
                    score to cachedApp
                } else {
                    null
                }
            }
            .sortedWith(compareByDescending<Pair<Int, CachedApp>> { it.first }
                .thenBy { it.second.appInfo.label.lowercase() })
            .map { it.second.appInfo }
        }
    }
}
