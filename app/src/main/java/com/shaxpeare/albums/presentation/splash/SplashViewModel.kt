package com.shaxpeare.albums.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.usecase.saveusers.SaveUsersUseCase
import com.shaxpeare.albums.presentation.common.BaseViewModel
import com.shaxpeare.albums.presentation.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed interface SplashEvent : Event {
    object LoadingSuccess : SplashEvent
    object LoadingFailed : SplashEvent
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val saveUsersUseCase: SaveUsersUseCase
) : BaseViewModel<SplashEvent>() {

    fun prefetchAndSaveUsers() {
        saveUsersUseCase().onEach {
            when(it){
                is Resource.Success -> {
                    sendEvent(SplashEvent.LoadingSuccess)
                } else -> {
                    // Do Nothing
                }
            }

        }.launchIn(viewModelScope)
    }
}
