package com.nemynew.materialsearch.ui.search

import android.Manifest
import androidx.compose.runtime.produceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import com.nemynew.materialsearch.MainActivity
import com.nemynew.materialsearch.util.IconPackManager 
import com.nemynew.materialsearch.util.IconPackInfo
import com.nemynew.materialsearch.data.SettingsRepository
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.zIndex
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.nemynew.materialsearch.R
import com.nemynew.materialsearch.data.AppInfo
import com.nemynew.materialsearch.data.AppRepository
import com.nemynew.materialsearch.data.ContactInfo
import com.nemynew.materialsearch.data.ContactRepository
import com.nemynew.materialsearch.data.UserPreferencesRepository
import com.nemynew.materialsearch.data.UserShortcutRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import android.content.Intent
import android.speech.RecognizerIntent
import android.net.Uri
import android.os.Build

data class SearchAction(
    val label: String,
    val icon: ImageVector,
    val url: String,
    val fallbackUrl: String? = null
)

private fun evaluateMath(expr: String): String? {
    val clean = expr.replace(" ", "").replace("×", "*").replace("÷", "/").replace("−", "-")
    if (clean.length < 3) return null 
    if (!clean.any { it in "0123456789" }) return null
    if (!clean.any { it in "+-*/" }) return null
    
    return try {
        val result = object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < clean.length) clean[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < clean.length) return Double.NaN
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm()
                    else if (eat('-'.code)) x -= parseTerm()
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code)) x *= parseFactor()
                    else if (eat('/'.code)) {
                        val divisor = parseFactor()
                        if (divisor == 0.0) return Double.NaN
                        x /= divisor
                    }
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()
                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch in '0'.code..'9'.code || ch == '.'.code) {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = clean.substring(startPos, pos).toDouble()
                } else {
                    return Double.NaN
                }
                return x
            }
        }.parse()
        
        if (result.isNaN()) return null
        android.util.Log.d("MaterialSearch", "Result for $expr: $result")
        if (result % 1.0 == 0.0) result.toLong().toString() else String.format("%.2f", result)
    } catch (e: Exception) {
        android.util.Log.e("MaterialSearch", "Fixing error: ${e.message}")
        null
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    startVoice: Boolean = false,
    shouldFocusSearch: Boolean = false,
    onVoiceTriggered: () -> Unit = {},
    onFocusTriggered: () -> Unit = {}
) {
    val context = LocalContext.current
    val appRepository = remember { AppRepository(context) }
    val contactRepository = remember { ContactRepository(context) }
    val userShortcutRepository = remember { UserShortcutRepository(context) }
    val settingsRepository = remember { SettingsRepository(context) }
    val userPreferencesRepository = remember { UserPreferencesRepository(context) }
    val iconPackManager = remember { IconPackManager(context) }
    
    var query by remember { mutableStateOf("") }
    var apps by remember { mutableStateOf(emptyList<AppInfo>()) }
    var contacts by remember { mutableStateOf(emptyList<ContactInfo>()) }
    
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current
    
    // Settings State
    val isSearchBarBottom by settingsRepository.isSearchBarBottom.collectAsState(initial = false)
    val autoShowKeyboard by settingsRepository.autoShowKeyboard.collectAsState(initial = true)
    val gridColumnCount by settingsRepository.gridColumnCount.collectAsState(initial = 4)
    val backgroundAlphaSetting by settingsRepository.backgroundAlpha.collectAsState(initial = 0.5f)
    val iconPackPackage by settingsRepository.iconPackPackage.collectAsState(initial = "")
    val iconSize by settingsRepository.iconSize.collectAsState(initial = 1.0f)
    val showIconLabels by settingsRepository.showIconLabels.collectAsState(initial = true)
    val pinnedApps by userPreferencesRepository.pinnedApps.collectAsState(initial = emptySet())
    val hiddenApps by userPreferencesRepository.hiddenApps.collectAsState(initial = emptySet())
    var showSettingsDialog by remember { mutableStateOf(false) }

    // Permissions
    var hasContactPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                 Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            hasContactPermission = isGranted
            if (isGranted) {
                scope.launch {
                    contactRepository.loadContacts()
                }
            }
        }
    )

    val recentAppsRepository = remember { com.nemynew.materialsearch.data.RecentAppsRepository(context) }
    
    // Enter Key Logic
    val openBestMatchOnEnter by settingsRepository.openBestMatchOnEnter.collectAsState(initial = true)
    
    fun onSearchAction() {
        if (openBestMatchOnEnter && query.isNotEmpty()) {
            val bestApp = apps.firstOrNull()
            val bestContact = if (hasContactPermission) contacts.firstOrNull() else null
            
            // Prioritize App over Contact if both exist (or based on list order)
            if (bestApp != null) {
                scope.launch { recentAppsRepository.addRecentApp(android.content.ComponentName(bestApp.packageName, bestApp.componentName).flattenToString()) }
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                context.startActivity(bestApp.intent)
                return
            } else if (bestContact != null) {
                 val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.withAppendedPath(android.provider.ContactsContract.Contacts.CONTENT_URI, bestContact.id)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
                return
            }
        }
        // Default behavior (hide keyboard) or web search if implemented later
        // focusManager.clearFocus() // require focusManager
    }

    val gridState = androidx.compose.foundation.lazy.grid.rememberLazyGridState()

    // Scroll to top when query changes
    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            gridState.scrollToItem(0)
        }
    }

    // User Shortcuts State
    val userShortcuts by userShortcutRepository.shortcutsFlow.collectAsState(initial = emptyMap())
    var showManageShortcutsDialog by remember { mutableStateOf(false) }

    // Add Shortcut Dialog State
    var showShortcutDialog by remember { mutableStateOf(false) }
    var selectedAppForShortcut by remember { mutableStateOf<AppInfo?>(null) }
    var shortcutText by remember { mutableStateOf("") }
    
    val voiceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == android.app.Activity.RESULT_OK) {
                val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
                if (spokenText != null) {
                    query = spokenText
                }
            }
        }
    )


    // App Management Dialog
    var showAppMenu by remember { mutableStateOf(false) }
    var selectedAppForMenu by remember { mutableStateOf<AppInfo?>(null) }

    if (showAppMenu && selectedAppForMenu != null) {
        AlertDialog(
            onDismissRequest = { showAppMenu = false },
            title = { Text(selectedAppForMenu?.label ?: "") },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAppMenu = false
                                selectedAppForShortcut = selectedAppForMenu
                                showShortcutDialog = true
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Edit, null)
                        Spacer(Modifier.width(16.dp))
                        Text("Add Custom Shortcut")
                    }
                    HorizontalDivider()
                    
                    // Pin/Unpin Option
                    val isPinned = selectedAppForMenu?.let { "${it.packageName}/${it.componentName}" in pinnedApps } ?: false
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAppMenu = false
                                scope.launch {
                                    if (selectedAppForMenu != null) {
                                        if (isPinned) {
                                            userPreferencesRepository.unpinApp(selectedAppForMenu!!.packageName, selectedAppForMenu!!.componentName)
                                        } else {
                                            userPreferencesRepository.pinApp(selectedAppForMenu!!.packageName, selectedAppForMenu!!.componentName)
                                        }
                                    }
                                }
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(if (isPinned) Icons.Default.Star else Icons.Default.Star, null, tint = if (isPinned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                        Spacer(Modifier.width(16.dp))
                        Text(if (isPinned) stringResource(R.string.menu_unpin_app) else stringResource(R.string.menu_pin_app))
                    }

                    // Hide Option
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAppMenu = false
                                scope.launch {
                                    if (selectedAppForMenu != null) {
                                        userPreferencesRepository.hideApp(selectedAppForMenu!!.packageName, selectedAppForMenu!!.componentName)
                                    }
                                }
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Clear, null) // Using Clear icon here
                        Spacer(Modifier.width(16.dp))
                        Text(stringResource(R.string.menu_hide_app))
                    }

                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAppMenu = false
                                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", selectedAppForMenu!!.packageName, null)
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Info, null)
                        Spacer(Modifier.width(16.dp))
                        Text(stringResource(R.string.menu_app_info))
                    }
                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAppMenu = false
                                val intent = Intent(Intent.ACTION_DELETE).apply {
                                    data = Uri.fromParts("package", selectedAppForMenu!!.packageName, null)
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(intent)
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Delete, null, tint = Color.Red)
                        Spacer(Modifier.width(16.dp))
                        Text(stringResource(R.string.menu_uninstall), color = Color.Red)
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showAppMenu = false }) {
                    Text(stringResource(R.string.button_cancel))
                }
            }
        )
    }

    // Add Shortcut Dialog
    if (showShortcutDialog && selectedAppForShortcut != null) {
        AlertDialog(
            onDismissRequest = { showShortcutDialog = false },
            title = { Text(stringResource(R.string.add_shortcut_title)) },
            text = {
                Column {
                    Text(stringResource(R.string.add_shortcut_text, selectedAppForShortcut?.label ?: ""))
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = shortcutText,
                        onValueChange = { shortcutText = it },
                        label = { Text(stringResource(R.string.shortcut_keyword_label)) },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (shortcutText.isNotBlank()) {
                            scope.launch {
                                userShortcutRepository.addShortcut(
                                    shortcut = shortcutText,
                                    packageName = selectedAppForShortcut!!.packageName,
                                    componentName = selectedAppForShortcut!!.componentName
                                )
                                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                showShortcutDialog = false
                                shortcutText = ""
                            }
                        }
                    }
                ) {
                    Text(stringResource(R.string.button_add))
                }
            },
            dismissButton = {
                TextButton(onClick = { showShortcutDialog = false }) {
                    Text(stringResource(R.string.button_cancel))
                }
            }
        )
    }

    // Manage Shortcuts Dialog
    if (showManageShortcutsDialog) {
        AlertDialog(
            onDismissRequest = { showManageShortcutsDialog = false },
            title = { Text(stringResource(R.string.manage_shortcuts)) },
            text = {
                Column {
                    Text(stringResource(R.string.manage_shortcuts_hint), style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider()
                    LazyColumn(modifier = Modifier.height(300.dp)) {
                        if (userShortcuts.isEmpty()) {
                            item {
                                Text(stringResource(R.string.no_shortcuts), modifier = Modifier.padding(8.dp))
                            }
                        }
                        items(userShortcuts.toList()) { (key, value) ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = key, style = MaterialTheme.typography.titleMedium)
                                    Text(text = value.split("|").firstOrNull() ?: "", style = MaterialTheme.typography.bodySmall)
                                }
                                IconButton(onClick = { scope.launch { userShortcutRepository.removeShortcut(key) } }) {
                                    Icon(Icons.Default.Delete, "Delete")
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showManageShortcutsDialog = false }) {
                    Text(stringResource(R.string.button_close))
                }
            }
        )
    }
    
    // Settings Screen

    // Recent Apps State
    val recentAppsFlow = remember { com.nemynew.materialsearch.data.RecentAppsRepository(context).recentApps }
    val recentAppPackageNames by recentAppsFlow.collectAsState(initial = emptyList())
    val recentApps = remember(recentAppPackageNames, apps) {
        // Filter apps list to find matching recent apps, preserving order
        recentAppPackageNames.mapNotNull { componentName ->
            apps.find { android.content.ComponentName(it.packageName, it.componentName).flattenToString() == componentName }
        }
    }
    // recentAppsRepository moved up

    LaunchedEffect(startVoice) {
        if (startVoice) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            }
            voiceLauncher.launch(intent)
            onVoiceTriggered()
        }
    }

    LaunchedEffect(shouldFocusSearch) {
        if (shouldFocusSearch) {
            query = "" // Reset query for fresh search
            // Wait a bit for window focus
            kotlinx.coroutines.delay(100)
            runCatching {
                focusRequester.requestFocus()
            }
            onFocusTriggered()
        }
    }

    LaunchedEffect(Unit) {
        appRepository.loadApps()
        apps = appRepository.searchApps("")
        
        // Request Contact Permission
        if (!hasContactPermission) {
            launcher.launch(Manifest.permission.READ_CONTACTS)
        } else {
            contactRepository.loadContacts()
        }

        kotlinx.coroutines.delay(300) // Wait for UI to be ready
        if (autoShowKeyboard && !startVoice) {
            runCatching {
                focusRequester.requestFocus()
            }
        }
    }

    LaunchedEffect(query, userShortcuts) {
        val allApps = appRepository.searchApps(query, userShortcuts)
            .distinctBy { "${it.packageName}/${it.componentName}" }
        apps = allApps.filter { 
            val key = "${it.packageName}/${it.componentName}"
            key !in hiddenApps
        }
        if (hasContactPermission) {
            contacts = contactRepository.searchContacts(query)
        }
    }

    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    val gridSpacing = when {
        gridColumnCount <= 4 -> 16.dp
        gridColumnCount == 5 -> 12.dp
        else -> 8.dp
    }
    val itemPadding = when {
        gridColumnCount <= 4 -> 8.dp
        gridColumnCount == 5 -> 4.dp
        else -> 2.dp
    }

    // Calculate exact width of one grid cell to match History icons with Grid icons
    // Content padding is 16.dp on each side (total 32.dp)
    val totalHorizontalPadding = 32.dp
    val itemWidth = (screenWidth - totalHorizontalPadding - (gridSpacing * (gridColumnCount - 1))) / gridColumnCount

    val isLightMode = !androidx.compose.foundation.isSystemInDarkTheme()
    val backgroundColor = if (isLightMode) {
        MaterialTheme.colorScheme.surfaceContainer // Slightly darker variant for light mode
    } else {
        MaterialTheme.colorScheme.surfaceContainerLow // Keep it bright for dark mode glass effect
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Use forced procedural blur (AGSL) as the primary background
        AcrylicBackground(
            modifier = Modifier.fillMaxSize(),
            blurIntensity = 1.0f, // Always use high quality procedural blur
            color = backgroundColor.copy(
                alpha = backgroundAlphaSetting
            ),
            isLightMode = isLightMode
        )

        // App List
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(gridColumnCount),
            contentPadding = PaddingValues(
                top = 16.dp + (if (!isSearchBarBottom) 76.dp + WindowInsets.systemBars.asPaddingValues().calculateTopPadding() else WindowInsets.systemBars.asPaddingValues().calculateTopPadding()),
                bottom = 16.dp + (if (isSearchBarBottom) 76.dp + WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() + WindowInsets.ime.asPaddingValues().calculateBottomPadding() else WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()),
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(gridSpacing),
            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
            modifier = Modifier.fillMaxSize()
        ) {
        // Math Result
        val mathResult = if (query.isNotEmpty()) evaluateMath(query) else null
        
        if (mathResult != null) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                   Column(
                       verticalArrangement = Arrangement.spacedBy(8.dp),
                       modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                   ) {
                        Text(
                            text = stringResource(R.string.calculator_result),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f))
                                .clickable {
                                    // Copy to clipboard or just keep
                                    val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                                    val clip = android.content.ClipData.newPlainText("Math Result", mathResult)
                                    clipboard.setPrimaryClip(clip)
                                    haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "= $mathResult",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                   }
                }
            }

            // Recent Apps Row
            if (recentApps.isNotEmpty() && query.isEmpty()) {
                 item(span = { GridItemSpan(maxLineSpan) }) {
                     Column {
                        Text(
                            text = stringResource(R.string.recent_section),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                        )
                        androidx.compose.foundation.lazy.LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                            contentPadding = PaddingValues(horizontal = gridSpacing / 2)
                        ) {
                            items(recentApps, key = { app -> "recent_${app.packageName}/${app.componentName}" }) { app ->
                                AppItem(
                                    app = app,
                                    iconPackManager = iconPackManager,
                                    iconPackPackage = iconPackPackage,
                                    iconSize = iconSize,
                                    showIconLabels = showIconLabels,
                                    itemPadding = itemPadding,
                                    modifier = Modifier.width(itemWidth),
                                    onClick = { 
                                        scope.launch { recentAppsRepository.addRecentApp(android.content.ComponentName(app.packageName, app.componentName).flattenToString()) }
                                        haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                        context.startActivity(app.intent) 
                                    },
                                    onLongClick = {
                                        haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                        selectedAppForMenu = app
                                        showAppMenu = true
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(16.dp))
                     }
                 }
            }


            // Contacts Section
            if (contacts.isNotEmpty()) {
                val bestMatchKey = if (query.isNotEmpty() && openBestMatchOnEnter && apps.isEmpty()) {
                    "contact_${contacts.first().id}"
                } else null

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = stringResource(R.string.contacts_section),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
                items(contacts, key = { contact -> "contact_${contact.id}" }) { contact ->
                    ContactItem(
                        contact = contact,
                        isBestMatch = "contact_${contact.id}" == bestMatchKey,
                        iconSize = iconSize,
                        itemPadding = itemPadding, // Pass itemPadding
                        onClick = {
                            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                                data = android.net.Uri.withAppendedPath(android.provider.ContactsContract.Contacts.CONTENT_URI, contact.id)
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            context.startActivity(intent)
                        }
                    )
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                     Spacer(modifier = Modifier.height(8.dp))
                     HorizontalDivider()
                     Spacer(modifier = Modifier.height(8.dp))
                }
            }
            
            // Pinned Apps Section (Only show when query is empty)
            val (pinnedList, unpinnedList) = if (query.isEmpty()) {
                apps.partition { "${it.packageName}/${it.componentName}" in pinnedApps }
            } else {
                Pair(emptyList(), apps)
            }

            if (pinnedList.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = stringResource(R.string.section_pinned),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
                items(pinnedList, key = { app -> "pinned_${app.packageName}/${app.componentName}" }) { app ->
                      AppItem(
                        app = app,
                        iconPackManager = iconPackManager,
                        iconPackPackage = iconPackPackage,
                        iconSize = iconSize,
                        showIconLabels = showIconLabels,
                        itemPadding = itemPadding,
                        onClick = { 
                            scope.launch { recentAppsRepository.addRecentApp(android.content.ComponentName(app.packageName, app.componentName).flattenToString()) }
                            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                            context.startActivity(app.intent) 
                        },
                        onLongClick = {
                            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                            selectedAppForMenu = app
                            showAppMenu = true
                        }
                    )
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                     Spacer(modifier = Modifier.height(8.dp))
                     HorizontalDivider()
                     Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Apps Section
            items(unpinnedList, key = { app -> "${app.packageName}/${app.componentName}" }) { app ->
                val isBestMatch = query.isNotEmpty() && openBestMatchOnEnter && 
                                 app == unpinnedList.firstOrNull() && pinnedList.isEmpty()
                AppItem(
                    app = app,
                    iconPackManager = iconPackManager,
                    iconPackPackage = iconPackPackage,
                    iconSize = iconSize,
                    showIconLabels = showIconLabels,
                    isBestMatch = isBestMatch,
                    itemPadding = itemPadding,
                    onClick = { 
                        scope.launch { recentAppsRepository.addRecentApp(android.content.ComponentName(app.packageName, app.componentName).flattenToString()) }
                        haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                        context.startActivity(app.intent) 
                    },
                    onLongClick = {
                        haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                        selectedAppForMenu = app
                        showAppMenu = true
                    }
                )
            }

            // Web Search Actions & No Results (Displayed when query is not empty)
            if (query.isNotEmpty()) {
                val hasResults = apps.isNotEmpty() || contacts.isNotEmpty() || mathResult != null
                
                if (!hasResults) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.no_results_found),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                   Column(
                       verticalArrangement = Arrangement.spacedBy(8.dp),
                       modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                   ) {
                       val searchActions = listOf(
                           SearchAction("Google", Icons.Default.Search, "https://www.google.com/search?q=$query"),
                           SearchAction("Play Store", Icons.Default.ShoppingCart, "https://play.google.com/store/search?q=$query&c=apps", "market://search?q=$query&c=apps"),
                           SearchAction("YouTube", Icons.Default.PlayArrow, "https://www.youtube.com/results?search_query=$query"),
                           SearchAction("YouTube Music", Icons.Default.PlayArrow, "https://music.youtube.com/search?q=$query"),
                           SearchAction("Spotify", Icons.Default.PlayArrow, "spotify:search:$query", "https://open.spotify.com/search/$query")
                       )

                       searchActions.forEach { action ->
                           Row(
                               verticalAlignment = Alignment.CenterVertically,
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .clip(RoundedCornerShape(12.dp))
                                   .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.8f))
                                   .clickable {
                                       haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                       try {
                                           val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(action.url)).apply {
                                               addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                           }
                                           context.startActivity(intent)
                                       } catch (e: Exception) {
                                           if (action.fallbackUrl != null) {
                                               val fallback = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(action.fallbackUrl)).apply {
                                                   addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                               }
                                               context.startActivity(fallback)
                                           }
                                       }
                                   }
                                   .padding(16.dp)
                           ) {
                               Icon(
                                   imageVector = action.icon,
                                   contentDescription = null,
                                   tint = MaterialTheme.colorScheme.onSurface,
                                   modifier = Modifier.size(24.dp)
                               )
                               Spacer(modifier = Modifier.width(16.dp))
                               Text(
                                   text = stringResource(R.string.search_on_web, action.label),
                                   style = MaterialTheme.typography.bodyLarge,
                                   color = MaterialTheme.colorScheme.onSurface
                               )
                           }
                       }
                   }
                }
            }
        }

        // Search Bar (No changes)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(if (isSearchBarBottom) Alignment.BottomCenter else Alignment.TopCenter)
                .then(
                     if (isSearchBarBottom) {
                         Modifier
                             .navigationBarsPadding()
                             .imePadding()
                             .padding(bottom = 16.dp)
                     } else {
                         Modifier
                             .statusBarsPadding()
                             .padding(top = 16.dp)
                     }
                )
                .padding(horizontal = 16.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.9f),
                shadowElevation = 4.dp,
                modifier = Modifier.fillMaxWidth().height(60.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    // Search Icon
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    if (query.isEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = androidx.compose.ui.res.stringResource(com.nemynew.materialsearch.R.string.search_hint),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            // Actual Input overlay
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go 
                ),
                keyboardActions = KeyboardActions(
                    onGo = { onSearchAction() },
                    onSearch = { onSearchAction() },
                    onDone = { onSearchAction() }
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .focusRequester(focusRequester)
                    .padding(start = 48.dp, end = 96.dp) // SIGNIFICANTLY increase END padding for Mic/Settings icons
                    .wrapContentHeight(Alignment.CenterVertically)
            )
            // Icons Row overlay (at the end of the search bar)
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { 
                            query = "" 
                            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    // Voice Search Button (only shown when query is empty)
                    IconButton(
                        onClick = {
                            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                                putExtra(RecognizerIntent.EXTRA_PROMPT, context.getString(R.string.speak_now))
                            }
                            try {
                                voiceLauncher.launch(intent)
                            } catch (e: Exception) {
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = stringResource(com.nemynew.materialsearch.R.string.speak_now),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                // Settings Button
                IconButton(
                    onClick = { showSettingsDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Settings Screen
        androidx.compose.animation.AnimatedVisibility(
            visible = showSettingsDialog,
            enter = androidx.compose.animation.slideInVertically(initialOffsetY = { it }),
            exit = androidx.compose.animation.slideOutVertically(targetOffsetY = { it })
        ) {
            com.nemynew.materialsearch.ui.settings.SettingsScreen(
                settingsRepository = settingsRepository,
                userPreferencesRepository = userPreferencesRepository,
                iconPackManager = iconPackManager,
                onClose = { showSettingsDialog = false },
                onOpenManageShortcuts = {
                    showSettingsDialog = false
                    showManageShortcutsDialog = true
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppItem(
    app: AppInfo,
    iconPackManager: IconPackManager,
    iconPackPackage: String,
    iconSize: Float,
    showIconLabels: Boolean,
    isBestMatch: Boolean = false,
    itemPadding: Dp = 8.dp, // Added itemPadding
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isPressed) 0.9f else 1f, label = "Scale")
    
    // Icon Loading Logic
    val iconBitmap by produceState<androidx.compose.ui.graphics.ImageBitmap?>(initialValue = null, key1 = app.packageName, key2 = app.componentName, key3 = iconPackPackage) {
        value = withContext(Dispatchers.IO) {
            try {
                val customIcon = if (iconPackPackage.isNotEmpty()) {
                    iconPackManager.getIcon(iconPackPackage, app.packageName, app.componentName)
                } else null

                if (customIcon != null) {
                    customIcon.toBitmap().asImageBitmap()
                } else {
                    context.packageManager.getActivityIcon(android.content.ComponentName(app.packageName, app.componentName)).toBitmap().asImageBitmap()
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    // Entrance Animation
    var targetAlpha by remember { mutableStateOf(0f) }
    var targetScale by remember { mutableStateOf(0.8f) }
    val alpha by animateFloatAsState(targetAlpha, tween(300))
    val scaleFactor by animateFloatAsState(targetScale, tween(300))

    LaunchedEffect(Unit) {
        targetAlpha = 1f
        targetScale = 1f
    }

    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .combinedClickable(
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect { interaction ->
                            when (interaction) {
                                is androidx.compose.foundation.interaction.PressInteraction.Press -> isPressed = true
                                is androidx.compose.foundation.interaction.PressInteraction.Release, 
                                is androidx.compose.foundation.interaction.PressInteraction.Cancel -> isPressed = false
                            }
                        }
                    }
                },
                indication = null,
                onClick = { 
                    haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                    onClick() 
                },
                onLongClick = {
                    haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                    onLongClick()
                }
            )
            .padding(itemPadding / 2)
            .graphicsLayer(
                alpha = alpha,
                scaleX = scaleFactor * scale,
                scaleY = scaleFactor * scale
            )
            .padding(2.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (isBestMatch) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else Color.Transparent)
                .then(
                    if (isBestMatch) Modifier.border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    else Modifier
                )
                .padding(all = itemPadding)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (iconBitmap != null) {
                    Image(
                        bitmap = iconBitmap!!,
                        contentDescription = app.label,
                        modifier = Modifier.size((48 * iconSize).dp)
                    )
                } else {
                    Box(modifier = Modifier.size((48 * iconSize).dp).background(Color.Gray, CircleShape))
                }
                
                if (showIconLabels) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = app.label,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            if (isBestMatch) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(18.dp)
                        .offset(x = 4.dp, y = (-4).dp)
                        .zIndex(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: ContactInfo, 
    isBestMatch: Boolean = false, 
    iconSize: Float = 1.0f,
    itemPadding: Dp = 8.dp,
    onClick: () -> Unit
) {
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current
    
    // Entrance animation
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(contact) {
        visible = true
    }
    
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "Alpha"
    )
    
    val scaleFactor by animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = tween(durationMillis = 300),
        label = "Scale"
    )

    // Press interaction
    var isPressed by remember { mutableStateOf(false) }
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        label = "PressScale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .graphicsLayer(
                alpha = alpha,
                scaleX = scaleFactor * pressScale,
                scaleY = scaleFactor * pressScale
            )
            .padding(itemPadding / 2)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (isBestMatch) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else Color.Transparent)
                .then(
                    if (isBestMatch) Modifier.border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    else Modifier
                )
                .clickable(
                    onClick = {
                        haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                        onClick()
                    },
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }.also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect { interaction ->
                                when (interaction) {
                                    is androidx.compose.foundation.interaction.PressInteraction.Press -> isPressed = true
                                    is androidx.compose.foundation.interaction.PressInteraction.Release -> isPressed = false
                                    is androidx.compose.foundation.interaction.PressInteraction.Cancel -> isPressed = false
                                }
                            }
                        }
                    }
                )
                .padding(all = itemPadding)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.size((56 * iconSize).dp) // Use iconSize
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size((32 * iconSize).dp) // Use iconSize
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (isBestMatch) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(18.dp)
                        .offset(x = 4.dp, y = (-4).dp)
                        .zIndex(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
}
