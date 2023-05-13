package com.shaxpeare.albums

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaxpeare.albums.navigation.NavGraph
import com.shaxpeare.albums.presentation.theme.AlbumsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumsTheme {
                navController = rememberNavController()
                AlbumsNavGraph()
            }
        }
    }

    @Composable
    private fun AlbumsNavGraph() {
        NavGraph(navController = navController, paddingValues = PaddingValues(all = 0.dp))
    }
}
