package com.shaxpeare.albums.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaxpeare.albums.R
import com.shaxpeare.albums.presentation.common.OnEvent

@Composable
fun SplashScreen(
    navigateToAlbums: () -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    splashViewModel.prefetchAndSaveUsers()

    OnEvent(event = splashViewModel.event, onEvent = {
        when(it){
            SplashEvent.LoadingSuccess -> navigateToAlbums.invoke()
            else -> {}
        }
    })

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

    SplashLoader(rotate = rotationAnim)

}

@Composable
fun SplashLoader(rotate: Float) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.rotate(rotate),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = stringResource(R.string.loading)
        )
    }
}
