package com.shaxpeare.albums.presentation.album.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.presentation.common.EmptyScreen
import com.shaxpeare.albums.presentation.common.ProgressView
import com.shaxpeare.albums.presentation.theme.Spacing

@Composable
fun ListContentCompact(
    albums: LazyPagingItems<Album>,
    navHostController: NavHostController,
    navigateToAlbumDetail: (Int) -> Unit
) {
    //val result = handlePagingResult(albums = albums)
//    if (result) {
    LazyColumn(
        modifier = Modifier.padding(all = MaterialTheme.Spacing.medium),
        contentPadding = PaddingValues(all = MaterialTheme.Spacing.none),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.Spacing.medium),
    ) {
        items(
            items = albums,
            key = { album -> album.id }
        ) { album ->
            if (album != null) {
                AlbumListItem(
                    navHostController = navHostController,
                    album = album,
                    navigateToAlbumDetail = navigateToAlbumDetail
                )
            }
        }

        when (val state = albums.loadState.refresh) {
            is LoadState.Error -> TODO()
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }

        when (val state = albums.loadState.append) {
            is LoadState.Error -> {}
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
//    }
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
