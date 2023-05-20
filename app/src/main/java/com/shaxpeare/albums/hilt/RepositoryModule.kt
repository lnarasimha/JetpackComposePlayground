package com.shaxpeare.albums.hilt

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.mapper.UserMapper
import com.shaxpeare.albums.data.network.AlbumsRemoteMediator
import com.shaxpeare.albums.data.network.AlbumsService
import com.shaxpeare.albums.data.repository.AlbumsRepositoryImpl
import com.shaxpeare.albums.data.repository.LocalRepositoryImpl
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import com.shaxpeare.albums.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Module to provide all Repository related dependencies.
 */
@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumsRepository(
        albumsService: AlbumsService,
        albumsDatabase: AlbumsDatabase,
        albumMapper: AlbumMapper,
        photoMapper: PhotoMapper,
        usersMapper:UserMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(
            albumsService,
            albumsDatabase,
            albumMapper,
            photoMapper,
            usersMapper,
            dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideLocalRepository(albumsDatabase: AlbumsDatabase): LocalRepository {
        return LocalRepositoryImpl(albumsDatabase)
    }

    @Singleton
    @Provides
    fun provideAlbumPager(
        albumsService: AlbumsService,
        albumsDatabase: AlbumsDatabase,
        albumMapper: AlbumMapper,
        photoMapper: PhotoMapper,
        userMapper: UserMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): Pager<Int, Album> {
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
                albumsDatabase.albumsDao().getAlbums()
            }
        )
    }
}
