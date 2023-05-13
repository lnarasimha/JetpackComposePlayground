package com.shaxpeare.albums.hilt

import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.network.AlbumsService
import com.shaxpeare.albums.data.repository.AlbumsRepositoryImpl
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCase
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(albumsService, albumsDatabase,albumMapper, photoMapper)
    }

    @Provides
    @Singleton
    fun provideGetAlbumsUseCase(albumsRepository: AlbumsRepository): GetAlbumsUseCase {
        return GetAlbumsUseCaseImpl(albumsRepository)
    }
}
