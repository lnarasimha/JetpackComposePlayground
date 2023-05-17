package com.shaxpeare.albums.presentation.album.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shaxpeare.albums.presentation.common.BackPressHandler

@Composable
fun AlbumDetailsScreen(
    albumDetailsViewModel: AlbumDetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = albumDetailsViewModel.state.value

    BackPressHandler(onBackPressed = {
        navController.popBackStack()
    })

    if (state > 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$state",
                style = MaterialTheme.typography.h1
            )
        }
    }
}
