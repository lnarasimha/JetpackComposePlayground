package com.shaxpeare.albums.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaxpeare.albums.R
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.presentation.album.list.components.handlePagingResult
import com.shaxpeare.albums.presentation.theme.ALBUM_ITEM_HEIGHT
import com.shaxpeare.albums.presentation.theme.Spacing


@Composable
fun ListContentLarge(
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
                                    withStyle(
                                        SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontSize = MaterialTheme.Spacing.textMedium,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    ) {
                                        append("Title: \n")
                                    }

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
                                fontSize = MaterialTheme.Spacing.textMedium
                            )

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontSize = MaterialTheme.Spacing.textMedium,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    ) {
                                        append("Album: \n")
                                    }
                                    append(album.title)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(top = MaterialTheme.Spacing.large),
                                style = MaterialTheme.typography.subtitle1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontSize = MaterialTheme.Spacing.textMedium,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    ) {
                                        append("Username: \n")
                                    }
                                    append(album.userName)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .heightIn(MaterialTheme.Spacing.large)
                                    .padding(
                                        top = MaterialTheme.Spacing.medium,
                                        bottom = MaterialTheme.Spacing.medium
                                    ),
                                style = MaterialTheme.typography.subtitle1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
}
