package com.shaxpeare.albums.presentation.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {
    fun getAlbums() = getAlbumsUseCase.getAlbumsWithPaging().cachedIn(viewModelScope)
}
