package com.shaxpeare.albums.presentation.album.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.presentation.common.EmptyScreen
import com.shaxpeare.albums.presentation.common.ListContentCompact
import com.shaxpeare.albums.presentation.common.ProgressView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlbumListScreen(
    navController: NavHostController,
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    navigateToDetails: (id: String) -> Unit
) {
    val lazyAlbumItems = albumListViewModel.getAlbums().collectAsLazyPagingItems()
    ListContentCompact(albums = lazyAlbumItems, navHostController = navController)
}

@Composable
fun handlePagingResult(
    albums: LazyPagingItems<Album>
): Boolean {

    albums.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
               ProgressView()
                false
            }

            error != null -> {
                EmptyScreen {
                    refresh()
                }
                false
            }

            albums.itemCount < 1 -> {
                false
            }

            else -> true
        }
    }
}
