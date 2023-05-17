package com.shaxpeare.albums.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.shaxpeare.albums.presentation.common.AlbumsContent
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter",
    "UnusedMaterialScaffoldPaddingParameter"
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.fetchingData.value
            }
        }
        setContent {
            AlbumsContent()
        }
    }
}
