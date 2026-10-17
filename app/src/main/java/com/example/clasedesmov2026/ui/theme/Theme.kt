
package com.example.clasedesmov2026.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4F46E5),
    onPrimary = Color.White,

    primaryContainer = Color(0xFFE0E7FF),
    onPrimaryContainer = Color(0xFF25205A),

    secondary = Color(0xFF0F766E),
    onSecondary = Color.White,

    tertiary = Color(0xFFB45309),
    onTertiary = Color.White,

    background = Color(0xFFF7F8FC),
    onBackground = Color(0xFF1F2937),

    surface = Color.White,
    onSurface = Color(0xFF1F2937),

    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFF64748B),

    outline = Color(0xFFCBD5E1)
)

@Suppress("UNUSED_PARAMETER")
@Composable
fun Clasedesmov2026Theme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
