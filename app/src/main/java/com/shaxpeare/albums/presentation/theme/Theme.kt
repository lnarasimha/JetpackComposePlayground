package com.shaxpeare.albums.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
val LightThemeColors = lightColors(
    primary = Color(0xFF971C1C),
    secondary =Color(0xFF831010),
    background = Color.White,
    surface = Color.White,
    error = Color(0xFFE50914),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color(0xFF1C1C1C),
    onError = Color.White,
)

val DarkThemeColors = darkColors(
    primary =  Color(0xFF971C1C),
    secondary = Color(0xFF831010),
    background = Color(0xFF1C1C1C),
    surface = Color(0xFF1C1C1C),
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color(0xFF1C1C1C),
)

@Composable
fun AlbumsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkThemeColors else LightThemeColors
    MaterialTheme(
        colors = colorScheme,
        typography = LightTypography,
        content = content
    )
}
