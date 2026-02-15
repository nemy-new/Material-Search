package com.nemynew.materialsearch.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DisplaySettings
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.nemynew.materialsearch.MainActivity
import com.nemynew.materialsearch.R
import com.nemynew.materialsearch.data.SettingsRepository
import com.nemynew.materialsearch.data.UserPreferencesRepository
import com.nemynew.materialsearch.util.IconPackManager
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.TextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsRepository: SettingsRepository,
    userPreferencesRepository: UserPreferencesRepository,
    iconPackManager: IconPackManager,
    onClose: () -> Unit,
    onOpenManageShortcuts: () -> Unit
) {
    val scope = rememberCoroutineScope()
    
    // State Collection
    val isSearchBarBottom by settingsRepository.isSearchBarBottom.collectAsState(initial = false)
    val autoShowKeyboard by settingsRepository.autoShowKeyboard.collectAsState(initial = true)
    val gridColumnCount by settingsRepository.gridColumnCount.collectAsState(initial = 4)
    val blurRadiusSetting by settingsRepository.blurRadius.collectAsState(initial = 60)
    val backgroundAlphaSetting by settingsRepository.backgroundAlpha.collectAsState(initial = 0.5f)
    val iconPackPackage by settingsRepository.iconPackPackage.collectAsState(initial = "")
    val iconSize by settingsRepository.iconSize.collectAsState(initial = 1.0f)
    val showIconLabels by settingsRepository.showIconLabels.collectAsState(initial = true)
    val isBlurEnabled by MainActivity.isBlurEnabled
    val hiddenApps by userPreferencesRepository.hiddenApps.collectAsState(initial = emptySet())
    var showHiddenAppsDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                windowInsets = WindowInsets.statusBars
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // General Section
            item {
                SettingsHeader(stringResource(R.string.behavior_section))
            }
            item {
                SettingsSwitch(
                    title = stringResource(R.string.auto_show_keyboard),
                    icon = Icons.Default.Keyboard,
                    checked = autoShowKeyboard,
                    onCheckedChange = { scope.launch { settingsRepository.setAutoShowKeyboard(it) } }
                )
            }
            item {
                SettingsItem(
                    title = stringResource(R.string.search_bar_position),
                    subtitle = if (isSearchBarBottom) stringResource(R.string.position_bottom) else stringResource(R.string.position_top),
                    icon = Icons.Default.ViewAgenda,
                    onClick = { scope.launch { settingsRepository.setSearchBarPosition(!isSearchBarBottom) } }
                )
            }

            // Appearance Section
            item {
                HorizontalDivider()
                SettingsHeader(stringResource(R.string.appearance_section))
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceBetween,
                         verticalAlignment = Alignment.CenterVertically
                    ) {
                         Row(verticalAlignment = Alignment.CenterVertically) {
                             Icon(Icons.Default.GridView, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                             Spacer(modifier = Modifier.width(16.dp))
                             Text(stringResource(R.string.grid_columns, gridColumnCount), style = MaterialTheme.typography.bodyLarge)
                         }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        (3..6).forEach { count ->
                            androidx.compose.material3.FilterChip(
                                selected = gridColumnCount == count,
                                onClick = { scope.launch { settingsRepository.setGridColumnCount(count) } },
                                label = { Text("$count") }
                            )
                        }
                    }
                }
            }
            
            item {
                SettingsSwitch(
                    title = stringResource(R.string.show_icon_labels),
                    icon = Icons.Default.TextFields,
                    checked = showIconLabels,
                    onCheckedChange = { scope.launch { settingsRepository.setShowIconLabels(it) } }
                )
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = stringResource(R.string.icon_size_label, (iconSize * 100).toInt()),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Slider(
                        value = iconSize,
                        onValueChange = { scope.launch { settingsRepository.setIconSize(it) } },
                        valueRange = 0.5f..1.5f,
                        steps = 9
                    )
                }
            }
            
            // Icon Pack
            item {
                 var expanded by remember { mutableStateOf(false) }
                 Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                     Row(
                         modifier = Modifier
                             .fillMaxWidth()
                             .clip(RoundedCornerShape(12.dp))
                             .clickable { expanded = !expanded }
                             .padding(vertical = 12.dp),
                         verticalAlignment = Alignment.CenterVertically
                     ) {
                         Icon(Icons.Default.Apps, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                         Spacer(modifier = Modifier.width(16.dp))
                         Column(modifier = Modifier.weight(1f)) {
                             Text(stringResource(R.string.icon_pack_section), style = MaterialTheme.typography.titleMedium)
                             Text(
                                 if (iconPackPackage.isEmpty()) stringResource(R.string.icon_pack_default) else iconPackPackage,
                                 style = MaterialTheme.typography.bodyMedium,
                                 color = MaterialTheme.colorScheme.onSurfaceVariant
                             )
                         }
                     }
                     
                     if (expanded) {
                         val installedPacks = remember { iconPackManager.getInstalledIconPacks() }
                         Column(
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .clip(RoundedCornerShape(12.dp))
                                 .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                         ) {
                             // Default
                             Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { scope.launch { settingsRepository.setIconPackPackage("") } }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                             ) {
                                 RadioButton(
                                     selected = iconPackPackage.isEmpty(),
                                     onClick = { scope.launch { settingsRepository.setIconPackPackage("") } }
                                 )
                                 Spacer(modifier = Modifier.width(12.dp))
                                 Text(stringResource(R.string.icon_pack_default))
                             }
                             HorizontalDivider()
                             // Installed Packs
                             installedPacks.forEach { pack ->
                                 Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { scope.launch { settingsRepository.setIconPackPackage(pack.packageName) } }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                 ) {
                                     RadioButton(
                                         selected = iconPackPackage == pack.packageName,
                                         onClick = { scope.launch { settingsRepository.setIconPackPackage(pack.packageName) } }
                                     )
                                     Spacer(modifier = Modifier.width(12.dp))
                                     if (pack.icon != null) {
                                         androidx.compose.foundation.Image(
                                             bitmap = pack.icon.toBitmap().asImageBitmap(),
                                             contentDescription = null,
                                             modifier = Modifier.size(32.dp)
                                         )
                                         Spacer(modifier = Modifier.width(12.dp))
                                     }
                                     Text(pack.label)
                                 }
                             }
                         }
                     }
                 }
            }

            // Background Section
            item {
                HorizontalDivider()
                SettingsHeader(stringResource(R.string.background_section))
            }
            
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        stringResource(R.string.background_blur, blurRadiusSetting),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Slider(
                        value = blurRadiusSetting.toFloat(),
                        onValueChange = { scope.launch { settingsRepository.setBlurRadius(it.toInt()) } },
                        valueRange = 0f..150f
                    )
                }
            }
            
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    val percentage = (backgroundAlphaSetting * 100).toInt()
                    Text(
                        stringResource(R.string.background_opacity, percentage),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Slider(
                        value = backgroundAlphaSetting,
                        onValueChange = { scope.launch { settingsRepository.setBackgroundAlpha(it) } },
                        valueRange = 0f..1f
                    )
                    
                    if (!isBlurEnabled) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f),
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = stringResource(R.string.blur_status_disabled),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.error,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = stringResource(R.string.blur_troubleshooting),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                SettingsHeader("Data")
            }

            item {
                SettingsItem(
                    title = stringResource(R.string.manage_shortcuts),
                    icon = Icons.Default.DisplaySettings,
                    onClick = onOpenManageShortcuts
                )
            }
            
            item {
                SettingsItem(
                    title = stringResource(R.string.settings_hidden_apps),
                     subtitle = if (hiddenApps.isEmpty()) stringResource(R.string.no_hidden_apps) else stringResource(R.string.settings_hidden_apps_desc),
                    icon = Icons.Default.VisibilityOff,
                    onClick = { showHiddenAppsDialog = true }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
    
    if (showHiddenAppsDialog) {
        val context = androidx.compose.ui.platform.LocalContext.current
        val pm = context.packageManager
        
        AlertDialog(
            onDismissRequest = { showHiddenAppsDialog = false },
            title = { Text(stringResource(R.string.settings_hidden_apps)) },
            text = {
                if (hiddenApps.isEmpty()) {
                    Text(stringResource(R.string.no_hidden_apps))
                } else {
                    LazyColumn {
                        items(hiddenApps.toList()) { key ->
                            val packageName = key.substringBefore("/")
                            // Try to get label
                            val label = remember(key) {
                                try {
                                    val info = pm.getApplicationInfo(packageName, 0)
                                    pm.getApplicationLabel(info).toString()
                                } catch (e: Exception) {
                                    packageName
                                }
                            }
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(label, modifier = Modifier.weight(1f))
                                TextButton(onClick = { scope.launch { userPreferencesRepository.unhideApp(key) } }) {
                                    Text(stringResource(R.string.action_unhide))
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showHiddenAppsDialog = false }) {
                    Text(stringResource(R.string.button_close))
                }
            }
        )
    }
}

@Composable
fun SettingsHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = subtitle?.let { { Text(it) } },
        leadingContent = icon?.let { { Icon(it, contentDescription = null) } },
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@Composable
fun SettingsSwitch(
    title: String,
    icon: ImageVector? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        leadingContent = icon?.let { { Icon(it, contentDescription = null) } },
        trailingContent = {
            Switch(checked = checked, onCheckedChange = onCheckedChange)
        },
        modifier = Modifier.clickable { onCheckedChange(!checked) }
    )
}
