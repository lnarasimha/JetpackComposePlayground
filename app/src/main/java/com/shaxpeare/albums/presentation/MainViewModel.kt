package com.shaxpeare.albums.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaxpeare.albums.domain.usecase.saveusers.SaveUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUsersUseCase: SaveUsersUseCase
) : ViewModel() {

    private val _fetchingData = MutableStateFlow(true)
    val fetchingData = _fetchingData.asStateFlow()

    init {
        viewModelScope.launch {
            saveUsersUseCase.invoke().collect {
                _fetchingData.value = false
            }
        }
    }
}
