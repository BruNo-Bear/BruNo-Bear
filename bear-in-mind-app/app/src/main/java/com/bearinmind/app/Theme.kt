package com.bearinmind.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BearTeal = Color(0xFF02565C)
private val BearGold = Color(0xFFCEBC6D)
private val BearCream = Color(0xFFF7F2E8)
private val BearInk = Color(0xFF14312F)

private val colors = lightColorScheme(
    primary = BearTeal,
    onPrimary = Color.White,
    primaryContainer = BearGold,
    onPrimaryContainer = BearInk,
    secondary = BearGold,
    onSecondary = BearInk,
    background = BearCream,
    onBackground = BearInk,
    surface = Color.White,
    onSurface = BearInk,
)

@Composable
fun BearInMindTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colors, typography = Typography(), content = content)
}
