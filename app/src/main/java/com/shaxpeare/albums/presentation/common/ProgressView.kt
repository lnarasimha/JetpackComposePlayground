package com.shaxpeare.albums.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shaxpeare.albums.R

@Composable
fun ProgressView(
) {
    val rotation = rememberInfiniteTransition()
    val rotationAnim by rotation.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    AnimatedProgressView(rotate = rotationAnim)

}

@Composable
fun AnimatedProgressView(rotate: Float) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.rotate(rotate),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Preview
@Composable
fun Preview() {
    ProgressView()
}
