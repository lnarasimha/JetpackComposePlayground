package com.shaxpeare.albums.domain.usecase.getalbums

import androidx.paging.PagingData
import com.shaxpeare.albums.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    fun getAlbumsWithPaging() : Flow<PagingData<Album>>
}
