package com.shaxpeare.albums.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.mapper.UserMapper
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.data.network.AlbumsPagingSource
import com.shaxpeare.albums.data.network.AlbumsRemoteMediator
import com.shaxpeare.albums.data.network.AlbumsService
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import com.shaxpeare.albums.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository Contract Implementation for network calls.
 */
class AlbumsRepositoryImpl @Inject constructor(
    private val albumsService: AlbumsService,
    private val albumsDatabase: AlbumsDatabase,
    private val albumMapper: AlbumMapper,
    private val photoMapper: PhotoMapper,
    private val userMapper: UserMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : AlbumsRepository {

    private val albumsDao = albumsDatabase.albumsDao()
    private lateinit var pager: Flow<PagingData<Album>>

    /**
     * Paging with only Network, using PagingSource
     */
    override fun getAlbumsWithNetworkPaging(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                prefetchDistance = 5,
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                AlbumsPagingSource(
                    albumsService,
                    albumMapper,
                    photoMapper
                )
            }
        ).flow
    }

    /**
     * Paging with only Local Cache and Network, using RemoteMediator.
     * To make App Start time smaller, the values are optimised to make less API calls.
     */
    @OptIn(ExperimentalPagingApi::class)
    override fun getAlbumsWithPaging(): Flow<PagingData<Album>> {

        return Pager(
            config = PagingConfig(
                prefetchDistance = 1,
                pageSize = 5,
                maxSize = 10,
                enablePlaceholders = true
            ),
            remoteMediator = AlbumsRemoteMediator(
                albumsService,
                albumsDatabase,
                albumMapper,
                photoMapper,
                userMapper,
                dispatcher
            ),
            pagingSourceFactory = {
                albumsDao.getAlbums()
            }
        ).flow
    }

    override suspend fun getAllAlbums(): List<ApiAlbum> = albumsService.getAllAlbums()

    override suspend fun getPhotosForAlbum(albumId: Int) = albumsService.getAlbumPhotos(albumId)

    override suspend fun getUsers(): List<ApiUser> = albumsService.getUsers()
}
