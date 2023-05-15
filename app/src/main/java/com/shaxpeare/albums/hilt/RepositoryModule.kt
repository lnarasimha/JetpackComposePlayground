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
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCase
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCaseImpl
import com.shaxpeare.albums.domain.usecase.saveusers.SaveUsersUseCase
import com.shaxpeare.albums.domain.usecase.saveusers.SaveUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(albumsService, albumsDatabase, albumMapper, photoMapper, dispatcher)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(albumsDatabase: AlbumsDatabase): LocalRepository {
        return LocalRepositoryImpl(albumsDatabase)
    }

    @Provides
    @Singleton
    fun provideGetAlbumsUseCase(albumsRepository: AlbumsRepository): GetAlbumsUseCase {
        return GetAlbumsUseCaseImpl(albumsRepository)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        localRepository: LocalRepository,
        albumsRepository: AlbumsRepository,
        usersMapper: UserMapper
    ): SaveUsersUseCase {
        return SaveUsersUseCaseImpl(
            localRepository,
            albumsRepository,
            usersMapper
        )
    }
}
