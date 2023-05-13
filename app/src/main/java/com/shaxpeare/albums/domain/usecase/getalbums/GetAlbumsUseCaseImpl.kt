package com.shaxpeare.albums.domain.usecase.getalbums

import androidx.paging.PagingData
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCaseImpl @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : GetAlbumsUseCase {

    override fun getAlbumsWithPaging(): Flow<PagingData<Album>> {
        return albumsRepository.getAlbumsWithPaging()
    }
}
