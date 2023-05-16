package com.shaxpeare.albums.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaxpeare.albums.presentation.theme.*

@Preview(showBackground = true)
@Composable
fun Shimmer() {
    ShimmerEffectLarge()
}

@Composable
fun ShimmerEffectLarge() {
    Column() {
        LazyColumn(
            modifier = Modifier
                .background(if (isSystemInDarkTheme()) Color.Black else Color.DarkGray)
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
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 0.8f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
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
            .alpha(alpha = alpha)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Surface(
                modifier = Modifier
                    .size(ALBUM_ITEM_HEIGHT)
                    .padding(all = MaterialTheme.Spacing.medium)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(MaterialTheme.Spacing.medium)),
                color = if (isSystemInDarkTheme())
                    ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = MaterialTheme.Spacing.small)
            ) {}

            repeat(3) {
                Surface(
                    modifier = Modifier
                        .height(32.dp)
                        .padding(
                            top = MaterialTheme.Spacing.medium,
                            end = MaterialTheme.Spacing.medium
                        )
                        .fillMaxWidth(0.7f),
                    color = if (isSystemInDarkTheme())
                        ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = MaterialTheme.Spacing.small)
                ) {}
                Spacer(modifier = Modifier.padding(all = MaterialTheme.Spacing.small))
            }
        }
    }
}
