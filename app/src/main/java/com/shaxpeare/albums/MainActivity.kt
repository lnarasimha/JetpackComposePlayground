package com.shaxpeare.albums

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaxpeare.albums.navigation.LocalDarkTheme
import com.shaxpeare.albums.navigation.LocalNavController
import com.shaxpeare.albums.navigation.NavGraph
import com.shaxpeare.albums.presentation.theme.AlbumsTheme
import com.shaxpeare.albums.presentation.theme.Spacing
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter",
    "UnusedMaterialScaffoldPaddingParameter"
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val systemTheme = isSystemInDarkTheme()
            val isDarkTheme = remember {
                mutableStateOf(systemTheme)
            }
            navController = rememberNavController()

            AlbumsTheme(isDarkTheme.value) {
                CompositionLocalProvider(
                    LocalNavController provides navController,
                    LocalDarkTheme provides isDarkTheme
                ) {
                    Scaffold(topBar = {
                        HomeTopBar(
                            isDarkTheme = LocalDarkTheme.current
                        )
                    },
                        content = { AlbumsNavGraph() }
                    )
                }
            }
        }
    }

    @Composable
    private fun AlbumsNavGraph() {
        NavGraph(paddingValues = PaddingValues(all = 0.dp))
    }

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
}
