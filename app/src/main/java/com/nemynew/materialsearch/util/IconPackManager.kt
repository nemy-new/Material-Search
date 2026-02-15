package com.nemynew.materialsearch.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.Locale

data class IconPackInfo(
    val packageName: String,
    val label: String,
    val icon: Drawable?
)

class IconPackManager(private val context: Context) {

    private val packageManager = context.packageManager
    private val iconPackResources = mutableMapOf<String, android.content.res.Resources>()
    private val appFilterMap = mutableMapOf<String, MutableMap<String, String>>() // package -> { component -> drawableName }
    
    // In-memory cache for loaded drawables logic could be added here if needed, 
    // but Glide/Coil is usually better for image caching handled at UI level. 
    // For now we just cache the mapping.

    fun getInstalledIconPacks(): List<IconPackInfo> {
        val iconPacks = mutableListOf<IconPackInfo>()
        val intent = Intent(Intent.ACTION_MAIN)
        
        // Common intent actions for icon packs
        val themes = setOf(
            "org.adw.launcher.THEMES",
            "com.gau.go.launcherex.theme",
            "com.novalauncher.THEME"
        )

        themes.forEach { action ->
            intent.action = action
            val resolvedInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA)
            for (info in resolvedInfos) {
                val packageName = info.activityInfo.packageName
                if (iconPacks.none { it.packageName == packageName }) {
                    val label = info.loadLabel(packageManager).toString()
                    val icon = info.loadIcon(packageManager)
                    iconPacks.add(IconPackInfo(packageName, label, icon))
                }
            }
        }
        return iconPacks
    }

    fun loadIconPack(packageName: String) {
        if (packageName.isEmpty()) return
        if (appFilterMap.containsKey(packageName)) return // Already loaded

        try {
            val resources = packageManager.getResourcesForApplication(packageName)
            iconPackResources[packageName] = resources
            
            val mapping = mutableMapOf<String, String>()
            val resId = resources.getIdentifier("appfilter", "xml", packageName)
            if (resId != 0) {
                val parser = resources.getXml(resId)
                var eventType = parser.eventType
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG && parser.name == "item") {
                        val component = parser.getAttributeValue(null, "component")
                        val drawable = parser.getAttributeValue(null, "drawable")
                        if (component != null && drawable != null) {
                            // component format example: ComponentInfo{com.example.app/com.example.app.MainActivity}
                            val cleanComponent = component.replace("ComponentInfo{", "").replace("}", "")
                            mapping[cleanComponent] = drawable
                        }
                    }
                    eventType = parser.next()
                }
            }
            appFilterMap[packageName] = mapping
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getIcon(iconPackPackage: String, appPackageName: String, componentName: String): Drawable? {
        if (iconPackPackage.isEmpty()) return null
        
        // Ensure pack is loaded
        if (!appFilterMap.containsKey(iconPackPackage)) {
            loadIconPack(iconPackPackage)
        }

        val mapping = appFilterMap[iconPackPackage] ?: return null
        val resources = iconPackResources[iconPackPackage] ?: return null

        val componentKey = "$appPackageName/$componentName"
        val drawableName = mapping[componentKey]
        
        if (drawableName != null) {
            val id = resources.getIdentifier(drawableName, "drawable", iconPackPackage)
            if (id != 0) {
                return try {
                    resources.getDrawable(id, null)
                } catch (e: Exception) {
                    null
                }
            }
        }
        
        // Fallback: Try looking up by package name only if component specific not found? 
        // Some packs use standard component names.
        
        // Create a default icon from the pack logic (iconback, iconmask etc) is complex 
        // and skipped for this MVP. We only return explicit replacements.

        return null
    }
}
