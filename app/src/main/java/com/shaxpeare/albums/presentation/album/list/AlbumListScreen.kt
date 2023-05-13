package com.shaxpeare.albums.presentation.album.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaxpeare.albums.R
import com.shaxpeare.albums.domain.model.Album


@Composable
fun AlbumListScreen(
    navController: NavHostController,
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    navigateToDetails: (id: String) -> Unit
) {

    val lazyAlbumItems = albumListViewModel.getAlbums().collectAsLazyPagingItems()
    AlbumsTopBar()
    ListContent(albums = lazyAlbumItems, navHostController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(Color.Gray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Albums", modifier = Modifier.fillMaxWidth())
        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    AlbumsTopBar()
}

@Composable
fun ListContent(
    albums: LazyPagingItems<Album>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(beers = albums)
    if (result) {
        LazyColumn(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(all = 16.dp),
            contentPadding = PaddingValues(all = 0.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = albums,
                key = { album ->
//                    Log.e("RESPONSE", album.toString())
                    album
                }
            ) { album ->
                if (album != null){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(Color.White),
                            verticalAlignment = Alignment.Top
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(all = 16.dp)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(16.dp)),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(data = album?.photos?.first()?.thumbnailUrl)
                                    .build(),
                                contentDescription = stringResource(id = R.string.app_name),
                                contentScale = ContentScale.Fit
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = album.photos.first().title,
                                    modifier = Modifier
                                        .padding(top = 16.dp, end = 16.dp)
                                        .fillMaxWidth(),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Text(
                                    text = album.title,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(top = 16.dp)
                                        .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = album.userName,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(top = 16.dp)
                                        .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    beers: LazyPagingItems<Album>
): Boolean {
    beers.apply {
        Log.e("PAGING", "")
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                false
            }

            error != null -> {
                false
            }

            beers.itemCount < 1 -> {

                false
            }

            else -> true
        }
    }
}
