package com.shaxpeare.albums.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.data.network.AlbumsPagingSource
import com.shaxpeare.albums.data.network.AlbumsRemoteMediator
import com.shaxpeare.albums.data.network.AlbumsService
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsService: AlbumsService,
    private val albumsDatabase: AlbumsDatabase,
    private val albumMapper: AlbumMapper,
    private val photoMapper: PhotoMapper,
) : AlbumsRepository {

    private val albumsDao = albumsDatabase.albumsDao()

//    override fun getAlbumsWithPaging(): Flow<PagingData<Album>> {
//        return Pager(
//            config = PagingConfig(
//                prefetchDistance = 5,
//                pageSize = 5,
//                maxSize = 20,
//                enablePlaceholders = true
//            ),
//            pagingSourceFactory = {
//                AlbumsPagingSource(albumsService, albumMapper, photoMapper)
//            }
//        ).flow
//    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getAlbumsWithPaging(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                prefetchDistance = 5,
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = true
            ),
            remoteMediator = AlbumsRemoteMediator(
                albumsService, albumsDatabase, albumMapper, photoMapper
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
