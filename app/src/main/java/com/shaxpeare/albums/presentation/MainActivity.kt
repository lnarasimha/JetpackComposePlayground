package com.shaxpeare.albums.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaxpeare.albums.navigation.LocalDarkTheme
import com.shaxpeare.albums.navigation.LocalNavController
import com.shaxpeare.albums.navigation.NavGraph
import com.shaxpeare.albums.presentation.common.HomeTopBar
import com.shaxpeare.albums.presentation.theme.AlbumsTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter",
    "UnusedMaterialScaffoldPaddingParameter"
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.fetchingData.value
            }
        }
        setContent {
            val systemTheme = isSystemInDarkTheme()
            val isDarkTheme = remember {
                mutableStateOf(systemTheme)
            }
            navController = rememberNavController()

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
    }

    @Composable
    private fun AlbumsNavGraph() {
        NavGraph(paddingValues = PaddingValues(all = 0.dp))
    }
}
