package com.shaxpeare.albums.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaxpeare.albums.navigation.LocalDarkTheme
import com.shaxpeare.albums.navigation.LocalNavController
import com.shaxpeare.albums.navigation.NavGraph
import com.shaxpeare.albums.presentation.theme.AlbumsTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AlbumsContent() {
    val systemTheme = isSystemInDarkTheme()
    val isDarkTheme = remember {
        mutableStateOf(systemTheme)
    }
    val navController: NavHostController = rememberNavController()

    AlbumsTheme(isDarkTheme.value) {
        CompositionLocalProvider(
            LocalNavController provides navController,
            LocalDarkTheme provides isDarkTheme
        ) {
            Scaffold(topBar = {
                HomeTopBar(
                    isDarkTheme = LocalDarkTheme.current
                )
            },
                content = { AlbumsNavGraph() }
            )
        }
    }
}

@Composable
private fun AlbumsNavGraph() {
    NavGraph( paddingValues = PaddingValues(all = 0.dp))
}
