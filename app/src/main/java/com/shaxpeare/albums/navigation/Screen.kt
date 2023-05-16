package com.shaxpeare.albums.navigation

const val SCREEN_ALBUM_LIST = "album_list_screen"
const val SCREEN_ALBUM_DETAIL = "album_details_screen"

sealed class Screen(val route: String) {
    object AlbumList : Screen(SCREEN_ALBUM_LIST)
    object AlbumDetails : Screen(SCREEN_ALBUM_DETAIL)
}
