package com.shaxpeare.albums.presentation.album.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val ARGUMENT_ALBUM_ID = "albumId"

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(-1)
    val state: State<Int> = _state

    init {
        savedStateHandle.get<Int>(ARGUMENT_ALBUM_ID)?.let { albumId ->
            _state.value = albumId
        }
    }
}
