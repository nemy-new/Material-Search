package com.nemynew.materialsearch.ui.search

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.withFrameMillis
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset

/**
 * A procedural AGSL shader that simulates a high-end acrylic/frosted glass effect.
 * This works on API 33+ (Android 13).
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private const val ACRYLIC_SHADER_SKSL = """
    uniform float2 iResolution;
    uniform float iTime;
    uniform float iBlurIntensity;
    uniform float4 iColor;
    uniform float iIsLightMode;

    float hash(float n) { return fract(sin(n) * 43758.5453123); }
    
    // Simplex-like noise for smoother gradients
    float noise(float2 p) {
        float2 i = floor(p);
        float2 f = fract(p);
        f = f * f * (3.0 - 2.0 * f);
        float n = i.x + i.y * 57.0;
        return mix(mix(hash(n + 0.0), hash(n + 1.0), f.x),
                  mix(hash(n + 57.0), hash(n + 58.0), f.x), f.y);
    }

    float fbm(float2 p) {
        float v = 0.0;
        float a = 0.5;
        float2 shift = float2(100.0);
        // More iterations for "finer" density
        for (int i = 0; i < 5; ++i) {
            v += a * noise(p);
            p = p * 2.2 + shift;
            a *= 0.5;
        }
        return v;
    }

    float4 main(float2 fragCoord) {
        float2 uv = fragCoord / iResolution.xy;
        float aspect = iResolution.x / iResolution.y;
        float2 p = uv;
        p.x *= aspect;
        
        // Multi-layered animated noise
        float t = iTime * 0.05;
        float n1 = fbm(p * 1.5 + t);
        float n2 = fbm(p * 3.0 - t * 0.5);
        float n3 = fbm(p * 6.0 + t * 0.2);
        
        // Blend layers for a "frosted" density map
        float density = (n1 * 0.5 + n2 * 0.3 + n3 * 0.2);
        
        // Calculate lighting based on density gradient (fake normal)
        float surface = smoothstep(0.2, 0.8, density);
        
        // Base color integration
        float3 col = iColor.rgb;
        
        // Add "frost" highlights and micro-depth
        // In light mode, we reduce the "additive" brightness to avoid wash out
        float tintScale = (iIsLightMode > 0.5) ? 0.05 : 0.1;
        col += (surface - 0.5) * tintScale;
        
        // Apply "grain" for that physical texture feeling
        float grain = (hash(fragCoord.x * 1.1 + fragCoord.y * 7.7 + iTime) - 0.5) * 0.05 * iBlurIntensity;
        col += grain;
        
        // Subtle "depth" glow from the center
        float dist = length(uv - 0.5);
        col += (1.0 - dist) * 0.05;

        // Final mix with base color to ensure it doesn't deviate too far
        return float4(col, iColor.a);
    }
"""

@Composable
fun AcrylicBackground(
    modifier: Modifier = Modifier,
    blurIntensity: Float = 1.0f,
    color: androidx.compose.ui.graphics.Color,
    isLightMode: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val shader = remember { RuntimeShader(ACRYLIC_SHADER_SKSL) }
        var time by remember { mutableFloatStateOf(0f) }
        
        LaunchedEffect(Unit) {
            while (true) {
                withFrameMillis { frameTime ->
                    time = frameTime / 1000f
                }
            }
        }
        
        Canvas(modifier = modifier.fillMaxSize()) {
            shader.setFloatUniform("iResolution", size.width, size.height)
            shader.setFloatUniform("iTime", time)
            shader.setFloatUniform("iBlurIntensity", blurIntensity)
            shader.setFloatUniform("iColor", color.red, color.green, color.blue, color.alpha)
            shader.setFloatUniform("iIsLightMode", if (isLightMode) 1.0f else 0.0f)
            
            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    this.shader = shader
                }
                canvas.nativeCanvas.drawRect(0f, 0f, size.width, size.height, paint)
            }
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // API 31+ RenderEffect fallback for real blur feel
        Box(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer {
                    renderEffect = android.graphics.RenderEffect.createBlurEffect(
                        30f * blurIntensity, 
                        30f * blurIntensity, 
                        android.graphics.Shader.TileMode.CLAMP
                    ).asComposeRenderEffect()
                }
                .background(color.copy(alpha = 0.4f))
        )
    } else {
        // Fallback for older devices: static semi-opaque background with subtle grain
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color.copy(alpha = 0.6f))
        ) {
            // Add a very subtle static grain to simulate frost texture
            Canvas(modifier = Modifier.fillMaxSize()) {
                val grainAlpha = 0.05f * blurIntensity
                val random = java.util.Random(42)
                for (i in 0..1000) {
                    val x = random.nextFloat() * size.width
                    val y = random.nextFloat() * size.height
                    drawCircle(
                        color = androidx.compose.ui.graphics.Color.White,
                        alpha = grainAlpha,
                        radius = 1.dp.toPx(),
                        center = androidx.compose.ui.geometry.Offset(x, y)
                    )
                }
            }
        }
    }
}
