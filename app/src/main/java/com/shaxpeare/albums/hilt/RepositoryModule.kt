package com.shaxpeare.albums.hilt

import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.mapper.UserMapper
import com.shaxpeare.albums.data.network.AlbumsService
import com.shaxpeare.albums.data.repository.AlbumsRepositoryImpl
import com.shaxpeare.albums.data.repository.LocalRepositoryImpl
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
}
