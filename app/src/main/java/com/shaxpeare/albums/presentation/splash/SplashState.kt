package com.shaxpeare.albums.presentation.splash

data class SplashState(
    val loading: Boolean = true,
    val loadedUsers: List<Long> = emptyList()
)
