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

    float hash(float n) { return fract(sin(n) * 43758.5453123); }
    
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
        for (int i = 0; i < 3; ++i) {
            v += a * noise(p);
            p = p * 2.0 + shift;
            a *= 0.5;
        }
        return v;
    }

    float4 main(float2 fragCoord) {
        float2 uv = fragCoord / iResolution.xy;
        
        // Multi-layered noise for "cloudy" glass look
        float t = iTime * 0.15;
        float n1 = fbm(uv * 2.0 + t);
        float n2 = fbm(uv * 4.0 - t * 0.8);
        
        // Base color
        float3 col = iColor.rgb;
        
        // Dynamic highlights and shadows
        float diffuse = smoothstep(0.3, 0.7, n1) * 0.15;
        float spec = pow(n2, 3.0) * 0.1;
        
        col += diffuse + spec;
        
        // Frosted texture (fine grain)
        float grain = (hash(fragCoord.x + fragCoord.y * 997.0 + iTime) - 0.5) * 0.04 * iBlurIntensity;
        col += grain;
        
        // Subtle vignette for depth
        float vignette = 1.0 - length(uv - 0.5) * 0.5;
        col *= vignette;

        return float4(col, iColor.a);
    }
"""

@Composable
fun AcrylicBackground(
    modifier: Modifier = Modifier,
    blurIntensity: Float = 1.0f,
    color: androidx.compose.ui.graphics.Color
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
            
            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    this.shader = shader
                }
                canvas.nativeCanvas.drawRect(0f, 0f, size.width, size.height, paint)
            }
        }
    } else {
        // Fallback for older devices: static semi-opaque background
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color.copy(alpha = 0.9f))
        )
    }
}
