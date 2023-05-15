package com.shaxpeare.albums.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Colors.statusBarColor
    get() = if (isLight) Purple700 else Color.Black

val Colors.welcomeScreenBackgroundColor
    get() = if (isLight) Color.White else Color.Black

val Colors.titleColor
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    get() = if (isLight) DarkGray.copy(alpha = 0.5f)
    else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicatorColor
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    get() = if (isLight) Purple500 else Purple700

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if (isLight) Purple500 else Color.Black

val BackgroundColorForLight = Color(0xFFFFFFFF)
val BackgroundColorForDark = Color(0xFF000000)
val SurfaceColorForLight = Color(0xFFFFFFFF)
val SurfaceColorForDark = Color(0xEE000000)
val SecondaryColorForLight = Color(0xFF3d8c31)
val SecondaryColorForDark = Color(0xFF50a03b)
val BlackColor = Color(0xFF000000)
val WhiteColor = Color(0xFFFFFFFF)
val PrimaryColor = Color(0xFF3d0482)
val PrimaryVariantColor = Color(0xFF6f0c97)
val OnBackgroundColor = Color(0xFF7b7b7b)
val ErrorColor=Color(0xFFed2b2b)

