package com.shaxpeare.albums.presentation.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.shaxpeare.albums.domain.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    val pager: Pager<Int, Album>
) : ViewModel() {
    fun getAlbums() = pager.flow
}
