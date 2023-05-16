package com.shaxpeare.albums.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.shaxpeare.albums.R
import com.shaxpeare.albums.presentation.theme.Spacing


@Composable
fun HomeTopBar(
    isDarkTheme: MutableState<Boolean>
) {
    TopAppBar(title = {
        TitleBar(isDarkTheme)
    })
}

@Composable
fun TitleBar(isDarkTheme: MutableState<Boolean>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            Modifier.padding(
                start = MaterialTheme.Spacing.medium,
                end = MaterialTheme.Spacing.medium
            ),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        val icon =
            if (!isDarkTheme.value) R.drawable.ic_dark_mode else R.drawable.ic_dark_mode_bright
        Icon(
            painterResource(id = icon),
            contentDescription = stringResource(R.string.toggle_theme),
            modifier = Modifier
                .padding(
                    start = MaterialTheme.Spacing.medium,
                    end = MaterialTheme.Spacing.medium
                )
                .clickable {
                    isDarkTheme.value = !isDarkTheme.value
                }
        )
    }
}
