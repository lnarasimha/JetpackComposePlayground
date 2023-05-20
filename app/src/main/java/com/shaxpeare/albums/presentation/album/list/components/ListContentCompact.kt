package com.shaxpeare.albums.presentation.album.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.presentation.theme.Spacing

@Composable
fun ListContentCompact(
    albums: LazyPagingItems<Album>,
    navigateToAlbumDetail: (Int) -> Unit
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = albums.loadState, block = {
        if (albums.loadState.refresh is LoadState.Error) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    })

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (albums.loadState.refresh == LoadState.Loading) {
            ShimmerEffect(count = 5)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.Spacing.medium)
            ) {
                items(
                    count = albums.itemCount,
                    key = albums.itemKey(),
                    contentType = albums.itemContentType(

                    )
                ) { index ->
                    val album = albums[index]
                    if (album != null) {
                        AlbumListItem(album = album, navigateToAlbumDetail = navigateToAlbumDetail)
                    }
                }

                item {
                    if (albums.loadState.append == LoadState.Loading) {
                        AnimatedShimmerItem()
                    }
                }
            }
        }
    }
}
