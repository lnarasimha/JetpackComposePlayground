package com.shaxpeare.albums.presentation.album.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaxpeare.albums.R
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.presentation.theme.ALBUM_ITEM_HEIGHT_COMPACT
import com.shaxpeare.albums.presentation.theme.Spacing

@Composable
fun AlbumListItem(
    navHostController: NavHostController,
    album: Album,
    navigateToAlbumDetail: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navigateToAlbumDetail.invoke(album.id)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(ALBUM_ITEM_HEIGHT_COMPACT)
                    .padding(all = MaterialTheme.Spacing.medium)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(MaterialTheme.Spacing.medium)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = album.photos.first().thumbnailUrl)
                    .build(),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = MaterialTheme.Spacing.textMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Title \n")
                        }
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
                    fontSize = MaterialTheme.Spacing.textSmall
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = MaterialTheme.Spacing.textSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Album \n")
                        }
                        append(album.title)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = MaterialTheme.Spacing.large,
                            end = MaterialTheme.Spacing.medium
                        ),
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.Spacing.textExtraSmall
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = MaterialTheme.Spacing.textSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Username \n")
                        }
                        append(album.userName)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(MaterialTheme.Spacing.large)
                        .padding(
                            top = MaterialTheme.Spacing.medium,
                            bottom = MaterialTheme.Spacing.medium,
                            end = MaterialTheme.Spacing.medium
                        ),
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.Spacing.textExtraSmall
                )
            }
        }
    }
}
