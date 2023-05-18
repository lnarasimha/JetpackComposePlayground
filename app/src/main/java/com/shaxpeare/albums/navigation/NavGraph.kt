package com.shaxpeare.albums.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shaxpeare.albums.presentation.album.details.AlbumDetailsScreen
import com.shaxpeare.albums.presentation.album.list.AlbumListScreen

val LocalNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
val LocalDarkTheme = compositionLocalOf { mutableStateOf(true) }

const val ARGUMENT_ALBUM_ID = "albumId"

@Composable
fun NavGraph(
    paddingValues: PaddingValues
) {
    val navController = LocalNavController.current
    NavHost(
        navController = navController,
        startDestination = Screen.AlbumList.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(
            route = Screen.AlbumList.route,
        ) {
            AlbumListScreen(
                navigateToDetails = {
                    navController.navigate("${Screen.AlbumDetails.route}/$it")
                })
        }

        composable(
            route = "${Screen.AlbumDetails.route}/{$ARGUMENT_ALBUM_ID}",
            arguments = listOf(navArgument(ARGUMENT_ALBUM_ID) {
                type = NavType.IntType
            })
        ) {
            AlbumDetailsScreen(navController = navController)
        }
    }
}
