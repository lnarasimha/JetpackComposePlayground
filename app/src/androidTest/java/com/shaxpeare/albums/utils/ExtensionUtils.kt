package com.shaxpeare.albums.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.navigation.compose.rememberNavController
import com.shaxpeare.albums.navigation.LocalNavController
import com.shaxpeare.albums.presentation.theme.AlbumsTheme


fun ComposeContentTestRule.setTestContent(content: @Composable BoxScope.() -> Unit) = setContent {
    AlbumsTheme() {
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            Surface(
                Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        }
    }
}

