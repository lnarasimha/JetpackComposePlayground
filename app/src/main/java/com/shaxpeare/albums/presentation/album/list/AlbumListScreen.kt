package com.shaxpeare.albums.presentation.album.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shaxpeare.albums.presentation.album.list.components.ListContentCompact

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlbumListScreen(
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    navigateToDetails: (id: Int) -> Unit
) {
    val lazyAlbumItems = albumListViewModel.getAlbums().collectAsLazyPagingItems()
    ListContentCompact(
        albums = lazyAlbumItems,
        navigateToDetails
    )
}
