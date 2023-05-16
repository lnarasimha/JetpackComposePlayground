package com.shaxpeare.albums.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.shaxpeare.albums.R

@Composable
fun EmptyScreen(onRetryNowClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "Launcher Icon"
            )
            Text(
                text = "Opps!! \n Looks like something is wrong\n Please try again later",
                modifier = Modifier.fillMaxWidth(0.6f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            Button(onClick = {
                onRetryNowClicked.invoke()
            }) {
                Text(text = "Retry Now !")
            }
        }
    }
}
