package com.nemynew.materialsearch.ui.setup

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.nemynew.materialsearch.R
import com.nemynew.materialsearch.data.SettingsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetupScreen(
    settingsRepository: SettingsRepository,
    onSetupComplete: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 7 }) 
    // 0: Welcome
    // 1: Permissions
    // 2: Tutorial Search
    // 3: Tutorial Shortcuts
    // 4: Tutorial Organize
    // 5: Customization
    // 6: Completion
    
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val currentGradient = when (pagerState.currentPage) {
        0 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.surfaceContainer))
        1 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surfaceContainer, MaterialTheme.colorScheme.secondaryContainer))
        2 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.tertiaryContainer))
        3 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.tertiaryContainer, MaterialTheme.colorScheme.primaryContainer))
        4 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.surfaceVariant))
        5 -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.surface))
        else -> Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.primaryContainer))
    }

    Scaffold(
        containerColor = Color.Transparent, // Handle background manually for smooth transitions
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Page Indicator
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val isSelected = pagerState.currentPage == iteration
                        val width by androidx.compose.animation.core.animateDpAsState(if (isSelected) 24.dp else 10.dp)
                        val color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                        Box(
                            modifier = Modifier
                                .height(10.dp)
                                .width(width)
                                .clip(CircleShape)
                                .background(color)
                        )
                    }
                }

                Button(
                    onClick = {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        } else {
                            scope.launch {
                                settingsRepository.setFirstRunCompleted()
                                onSetupComplete()
                            }
                        }
                    },
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        if (pagerState.currentPage < pagerState.pageCount - 1) 
                            stringResource(R.string.setup_button_next) 
                        else 
                            stringResource(R.string.setup_button_get_started)
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Default.ArrowForward, null, modifier = Modifier.size(16.dp))
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(currentGradient)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) { page ->
                when (page) {
                    0 -> WelcomePage()
                    1 -> PermissionsPage()
                    2 -> TutorialSearchPage()
                    3 -> TutorialShortcutsPage()
                    4 -> TutorialOrganizePage()
                    5 -> CustomizationPage(settingsRepository)
                    6 -> CompletionPage()
                }
            }
        }
    }
}

@Composable
fun WelcomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.setup_welcome_title),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.setup_welcome_subtitle),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun PermissionsPage() {
    val context = LocalContext.current
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
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.setup_permissions_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.setup_permissions_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(48.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.setup_permissions_contact),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                
                if (hasContactPermission) {
                    Icon(
                        Icons.Default.Check,
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    Button(onClick = { launcher.launch(Manifest.permission.READ_CONTACTS) }) {
                        Text(stringResource(R.string.setup_button_grant))
                    }
                }
            }
        }
    }
}

@Composable
fun TutorialSearchPage() {
    var typedText by remember { mutableStateOf("") }
    val targetText = "settings"
    
    LaunchedEffect(Unit) {
        delay(500)
        targetText.forEachIndexed { index, _ ->
            typedText = targetText.substring(0, index + 1)
            delay(150)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        // Fake UI
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .padding(16.dp)
        ) {
             Text(
                 text = if(typedText.isEmpty()) stringResource(R.string.setup_hint_search) else typedText,
                 style = MaterialTheme.typography.titleLarge,
                 color = if(typedText.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface 
             )
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = stringResource(R.string.setup_tutorial_search_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.setup_tutorial_search_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun TutorialShortcutsPage() {
    var showDialog by remember { mutableStateOf(false) }
    var typedShortcut by remember { mutableStateOf("") }
    val targetShortcut = "yt"
    
    LaunchedEffect(Unit) {
        delay(1000)
        showDialog = true
        delay(500)
        targetShortcut.forEachIndexed { index, _ ->
            typedShortcut = targetShortcut.substring(0, index + 1)
            delay(150)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        // Mock App Item
        Card(
             colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
             modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
             Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                 Box(Modifier.size(40.dp).background(Color.Red, CircleShape))
                 Spacer(Modifier.width(16.dp))
                 Text("YouTube", style = MaterialTheme.typography.titleMedium)
             }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        AnimatedVisibility(visible = showDialog) {
             Card(
                 colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright),
                 elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                 modifier = Modifier.align(Alignment.CenterHorizontally)
             ) {
                 Column(modifier = Modifier.padding(16.dp)) {
                      Text(stringResource(R.string.setup_hint_shortcut))
                      Spacer(modifier = Modifier.height(8.dp))
                      Text(
                          text = typedShortcut.ifEmpty { "|" },
                          style = MaterialTheme.typography.bodyLarge,
                          color = MaterialTheme.colorScheme.primary
                      )
                 }
             }
        }

        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = stringResource(R.string.setup_tutorial_shortcuts_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.setup_tutorial_shortcuts_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun TutorialOrganizePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
             // Pin Card
             Card(
                 modifier = Modifier.weight(1f),
                 colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
             ) {
                 Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                     Icon(Icons.Default.Star, null, modifier = Modifier.size(32.dp))
                     Spacer(Modifier.height(8.dp))
                     Text(stringResource(R.string.menu_pin_app), textAlign = TextAlign.Center)
                 }
             }
             
             // Hide Card
             Card(
                 modifier = Modifier.weight(1f),
                 colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
             ) {
                 Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                     Icon(Icons.Default.VisibilityOff, null, modifier = Modifier.size(32.dp))
                     Spacer(Modifier.height(8.dp))
                     Text(stringResource(R.string.menu_hide_app), textAlign = TextAlign.Center)
                 }
             }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = stringResource(R.string.setup_tutorial_organize_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.setup_tutorial_organize_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun CustomizationPage(settingsRepository: SettingsRepository) {
    val isSearchBarBottom by settingsRepository.isSearchBarBottom.collectAsState(initial = false)
    val autoShowKeyboard by settingsRepository.autoShowKeyboard.collectAsState(initial = true)
    val scope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.setup_customization_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        // Search Bar Position
         Card(
             modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
             colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
         ) {
              Column(modifier = Modifier.padding(16.dp)) {
                  Text(stringResource(R.string.search_bar_position), style = MaterialTheme.typography.titleMedium)
                  Spacer(modifier = Modifier.height(8.dp))
                  Row(verticalAlignment = Alignment.CenterVertically) {
                      RadioButton(
                          selected = !isSearchBarBottom,
                          onClick = { scope.launch { settingsRepository.setSearchBarPosition(false) } }
                      )
                      Text(stringResource(R.string.position_top))
                      Spacer(modifier = Modifier.width(16.dp))
                      RadioButton(
                          selected = isSearchBarBottom,
                          onClick = { scope.launch { settingsRepository.setSearchBarPosition(true) } }
                      )
                      Text(stringResource(R.string.position_bottom))
                  }
              }
         }
         
         Spacer(modifier = Modifier.height(16.dp))

         // Auto Show Keyboard
         Card(
             modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
             colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
         ) {
             Row(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(16.dp),
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween
             ) {
                 Row(verticalAlignment = Alignment.CenterVertically) {
                     Icon(Icons.Default.Keyboard, null)
                     Spacer(modifier = Modifier.width(16.dp))
                     Text(stringResource(R.string.auto_show_keyboard))
                 }
                 Switch(
                     checked = autoShowKeyboard,
                     onCheckedChange = { scope.launch { settingsRepository.setAutoShowKeyboard(it) } }
                 )
             }
         }
    }
}

@Composable
fun CompletionPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
         Text(
            text = stringResource(R.string.setup_completion_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.setup_completion_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
