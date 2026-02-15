package com.nemynew.materialsearch

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.nemynew.materialsearch.ui.theme.MaterialSearchTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import java.util.function.Consumer
import android.view.WindowManager
import android.graphics.PixelFormat

class MainActivity : ComponentActivity() {
    companion object {
        var isBlurEnabled = mutableStateOf(true)
    }

    private val blurEnabledListener = Consumer<Boolean> { enabled ->
        isBlurEnabled.value = enabled
    }

    private val startVoiceSignal = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        // Ensure system bars are transparent and logic is consistent
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        
        // Some devices require FLAG_DIM_BEHIND to enable blur
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes.dimAmount = 0f
        window.setFormat(PixelFormat.TRANSLUCENT)
        
        val settingsRepository = com.nemynew.materialsearch.data.SettingsRepository(this)
        
        // Enable blur behind for API 31+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            window.addFlags(android.view.WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
            
            // Observe blur radius changes
            lifecycleScope.launch {
                settingsRepository.blurRadius.collect { radius ->
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                        window.attributes.blurBehindRadius = radius
                        window.setBackgroundBlurRadius(radius)
                        window.attributes = window.attributes // Force update
                    }
                }
            }

            // Monitor real-time blur availability
            isBlurEnabled.value = windowManager.isCrossWindowBlurEnabled
            try {
                windowManager.addCrossWindowBlurEnabledListener(blurEnabledListener)
            } catch (e: Exception) {
                isBlurEnabled.value = false
            }
        } else {
            isBlurEnabled.value = false
        }
        
        // This is crucial for the transparent background effect
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) {
            // Semi-transparent background that works for both light/dark on older devices
            window.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(0xAA000000.toInt()))
        } else {
            // Samsung One UI sometimes needs a non-null, near-transparent background to trigger the blur engine
            window.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(0x01000000)) 
        }

        // Check if we should start with voice search (from shortcut or tile)
        handleIntent(intent)
        
        setContent {
            MaterialSearchTheme {
                // Surface with transparent color to allow window background to show through
                // Fallback: If blur is not supported, we use a more opaque background in SearchScreen
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val isFirstRun = settingsRepository.isFirstRun.collectAsState(initial = true)

                    if (isFirstRun.value) {
                        com.nemynew.materialsearch.ui.setup.SetupScreen(
                            settingsRepository = settingsRepository,
                            onSetupComplete = {
                                // Transition will happen automatically as isFirstRun becomes false
                            }
                        )
                    } else {
                        com.nemynew.materialsearch.ui.search.SearchScreen(
                            startVoice = startVoiceSignal.value,
                            onVoiceTriggered = { startVoiceSignal.value = false }
                        )
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.getBooleanExtra("EXTRA_START_VOICE", false) == true) {
            startVoiceSignal.value = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            try {
                windowManager.removeCrossWindowBlurEnabledListener(blurEnabledListener)
            } catch (e: Exception) {}
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialSearchTheme {
        Greeting("Android")
    }
}
