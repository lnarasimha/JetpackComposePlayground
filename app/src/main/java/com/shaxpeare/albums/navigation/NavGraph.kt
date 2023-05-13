package com.shaxpeare.albums.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shaxpeare.albums.presentation.album.details.AlbumDetailsScreen
import com.shaxpeare.albums.presentation.album.list.AlbumListScreen
import com.shaxpeare.albums.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.AlbumList.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(route = Screen.AlbumList.route) {
            AlbumListScreen(navController = navController,
                navigateToDetails = {
                    navController.navigate(Screen.AlbumDetails.route)
                })
        }

        composable(route = Screen.Splash.route) {
            SplashScreen(navigateToAlbums = {
                navController.navigate(Screen.AlbumList.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = Screen.AlbumDetails.route) {
            AlbumDetailsScreen()
        }
    }
}
