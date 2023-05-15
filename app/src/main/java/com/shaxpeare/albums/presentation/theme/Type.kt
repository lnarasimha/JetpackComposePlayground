package com.shaxpeare.albums.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.shaxpeare.albums.R

private val Default = Typography()
val LightTypography = Typography(
    h1 = Default.h1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 32.sp,
    ),
    h2 = Default.h2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 24.sp,
    ),
    h3 = Default.h3.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 20.sp,
    ),
    body1 = Default.body1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 18.sp,
    ),
    body2 = Default.body2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 16.sp,
    ),
    subtitle1 = Default.subtitle1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp,
    ),
    subtitle2 = Default.subtitle2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 12.sp,
    ),
)

//private val Montserrat = FontFamily(
//    Font(R.font.montserrat_regular),
//    Font(R.font.montserrat_medium, FontWeight.W500)
//)
//
//@Suppress("DEPRECATION")
//val defaultTextStyle = TextStyle(
//    fontFamily = Montserrat,
//    platformStyle = PlatformTextStyle(
//        includeFontPadding = false
//    ),
//    lineHeightStyle = LineHeightStyle(
//        alignment = LineHeightStyle.Alignment.Center,
//        trim = LineHeightStyle.Trim.None
//    )
//)
//
//val AlbumsTypography = Typography(
//    displayLarge = defaultTextStyle.copy(
//        fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp
//    ),
//    displayMedium = defaultTextStyle.copy(
//        fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp
//    ),
//    displaySmall = defaultTextStyle.copy(
//        fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp
//    ),
//    headlineLarge = defaultTextStyle.copy(
//        fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
//    ),
//    headlineMedium = defaultTextStyle.copy(
//        fontSize = 28.sp, lineHeight = 36.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
//    ),
//    headlineSmall = defaultTextStyle.copy(
//        fontSize = 24.sp, lineHeight = 32.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
//    ),
//    titleLarge = defaultTextStyle.copy(
//        fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
//    ),
//    titleMedium = defaultTextStyle.copy(
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.15.sp,
//        fontWeight = FontWeight.Medium,
//        lineBreak = LineBreak.Heading
//    ),
//    titleSmall = defaultTextStyle.copy(
//        fontSize = 14.sp,
//        lineHeight = 20.sp,
//        letterSpacing = 0.1.sp,
//        fontWeight = FontWeight.Medium,
//        lineBreak = LineBreak.Heading
//    ),
//    labelLarge = defaultTextStyle.copy(
//        fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp, fontWeight = FontWeight.Medium
//    ),
//    labelMedium = defaultTextStyle.copy(
//        fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium
//    ),
//    labelSmall = defaultTextStyle.copy(
//        fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium
//    ),
//    bodyLarge = defaultTextStyle.copy(
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp,
//        lineBreak = LineBreak.Paragraph
//    ),
//    bodyMedium = defaultTextStyle.copy(
//        fontSize = 14.sp,
//        lineHeight = 20.sp,
//        letterSpacing = 0.25.sp,
//        lineBreak = LineBreak.Paragraph
//    ),
//    bodySmall = defaultTextStyle.copy(
//        fontSize = 12.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.4.sp,
//        lineBreak = LineBreak.Paragraph
//    ),
//)
