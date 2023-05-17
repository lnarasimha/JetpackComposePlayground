package com.shaxpeare.albums.presentation.album.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.shaxpeare.albums.presentation.album.list.components.ListContentCompact

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlbumListScreen(
    navController: NavHostController,
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    navigateToDetails: (id: Int) -> Unit
) {
    Log.e("Error", "Called now ${System.currentTimeMillis()}")
    val lazyAlbumItems = albumListViewModel.getAlbums().collectAsLazyPagingItems()
    ListContentCompact(
        albums = lazyAlbumItems,
        navHostController = navController,
        navigateToDetails
    )
}
