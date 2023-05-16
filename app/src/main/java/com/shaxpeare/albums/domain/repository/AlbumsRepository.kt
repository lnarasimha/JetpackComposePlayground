package com.shaxpeare.albums.domain.repository

import androidx.paging.PagingData
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.Album
import kotlinx.coroutines.flow.Flow

/**
 * Contract for Network Repository.
 */
interface AlbumsRepository {
    fun getAlbumsWithNetworkPaging(): Flow<PagingData<Album>>
    fun getAlbumsWithPaging(): Flow<PagingData<Album>>
    suspend fun getAllAlbums(): List<ApiAlbum>
    suspend fun getPhotosForAlbum(albumId: Int): List<ApiPhoto>
    suspend fun getUsers(): List<ApiUser>
}
