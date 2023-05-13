package com.shaxpeare.albums.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object AlbumList : Screen("album_list_screen")
    object AlbumDetails : Screen("album_details_screen")
}
