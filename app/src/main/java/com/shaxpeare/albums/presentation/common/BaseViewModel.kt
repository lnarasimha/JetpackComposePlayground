package com.shaxpeare.albums.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : Event> : ViewModel() {
    private val _eventChannel = Channel<E>()
    val event = _eventChannel.receiveAsFlow().shareIn(viewModelScope, SharingStarted.Lazily)
    protected suspend fun sendEvent(event: E) = _eventChannel.send(event)
    protected fun sendEventSync(event: E) = viewModelScope.launch { _eventChannel.send(event) }
}
