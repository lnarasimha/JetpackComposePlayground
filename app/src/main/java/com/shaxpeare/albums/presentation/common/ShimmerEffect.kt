package com.shaxpeare.albums.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaxpeare.albums.R

@Composable
fun ShimmerEffect() {
    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(all = 16.dp),
        contentPadding = PaddingValues(all = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = 2
        ) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
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
                    .data(data = "it?.photos?.first()?.thumbnailUrl")
                    .build(),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "it?.photos?.first()?.title!!",
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "it?.photos?.first()?.title!!",
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
                    text = "it?.photos?.first()?.title!!",
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

@Composable
fun ShimmerContent(
) {

    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(all = 16.dp),
        contentPadding = PaddingValues(all = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = 2
        ) {

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
                            .data(data = "it?.photos?.first()?.thumbnailUrl")
                            .build(),
                        contentDescription = stringResource(id = R.string.app_name),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "it?.photos?.first()?.title!!",
                            modifier = Modifier
                                .padding(top = 16.dp, end = 16.dp)
                                .fillMaxWidth(),
                            color = Color.Black,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = "it?.photos?.first()?.title!!",
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
                            text = "it?.photos?.first()?.title!!",
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
