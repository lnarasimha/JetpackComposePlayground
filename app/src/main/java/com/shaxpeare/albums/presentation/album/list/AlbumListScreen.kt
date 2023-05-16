package com.shaxpeare.albums.presentation.album.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.shaxpeare.albums.presentation.common.ShimmerEffect
import com.shaxpeare.albums.presentation.theme.ALBUM_ITEM_HEIGHT
import com.shaxpeare.albums.presentation.theme.Spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlbumListScreen(
    navController: NavHostController,
    albumListViewModel: AlbumListViewModel = hiltViewModel(),
    navigateToDetails: (id: String) -> Unit
) {
    val lazyAlbumItems = albumListViewModel.getAlbums().collectAsLazyPagingItems()
    ListContent(albums = lazyAlbumItems, navHostController = navController)
}

@Composable
fun ListContent2(
    albums: LazyPagingItems<Album>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(albums = albums)
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
                if (album != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
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
                                    text = buildAnnotatedString {
                                        append("Title: ")
                                        append(album.photos.first().title)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = MaterialTheme.Spacing.medium,
                                            end = MaterialTheme.Spacing.medium
                                        ),
                                    style = MaterialTheme.typography.subtitle1,
                                    textAlign = TextAlign.Center,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = buildAnnotatedString {
                                        append("Album: ")
                                        append(album.title)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = MaterialTheme.Spacing.large,
                                            end = MaterialTheme.Spacing.medium
                                        )
                                        .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                    style = MaterialTheme.typography.subtitle1,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = buildAnnotatedString {
                                        append("Username: ")
                                        append(album.userName)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(MaterialTheme.Spacing.large)
                                        .padding(
                                            top = MaterialTheme.Spacing.medium,
                                            bottom = MaterialTheme.Spacing.medium,
                                            end = MaterialTheme.Spacing.medium
                                        )
                                        .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                    style = MaterialTheme.typography.subtitle1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
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
fun ListContent(
    albums: LazyPagingItems<Album>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(albums = albums)
    if (result) {
        LazyColumn(
            modifier = Modifier
                .padding(all = MaterialTheme.Spacing.none),
            contentPadding = PaddingValues(all = MaterialTheme.Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.Spacing.medium)
        ) {
            items(
                items = albums,
                key = { album ->
                    album
                }
            ) { album ->
                if (album != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = MaterialTheme.Spacing.extraSmall,
                        shape = RoundedCornerShape(MaterialTheme.Spacing.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(ALBUM_ITEM_HEIGHT)
                                    .padding(all = MaterialTheme.Spacing.medium)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(MaterialTheme.Spacing.medium)),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(data = album.photos.first().thumbnailUrl)
                                    .build(),
                                contentDescription = stringResource(id = R.string.app_name),
                                contentScale = ContentScale.Fit
                            )

                            Text(
                                text = buildAnnotatedString {
                                    append("Title: ")
                                    append(album.photos.first().title)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(
                                        end = MaterialTheme.Spacing.medium
                                    ),
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 24.sp
                            )

                            Text(
                                text = buildAnnotatedString {
                                    append("Album: ")
                                    append(album.title)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(top = MaterialTheme.Spacing.large)
                                    .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                style = MaterialTheme.typography.subtitle1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )

                            Text(
                                text = buildAnnotatedString {
                                    append("Username: ")
                                    append(album.userName)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .heightIn(MaterialTheme.Spacing.large)
                                    .padding(
                                        top = MaterialTheme.Spacing.medium,
                                        bottom = MaterialTheme.Spacing.medium
                                    )
                                    .background(Color.LightGray, RoundedCornerShape(6.dp)),
                                style = MaterialTheme.typography.subtitle1,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
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
                ShimmerEffect()
                false
            }

            error != null -> {
                false
            }

            albums.itemCount < 1 -> {
                false
            }

            else -> true
        }
    }
}

@Composable
fun ProgressView() {
    Box(Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = "Loading.."
        )
    }
}
