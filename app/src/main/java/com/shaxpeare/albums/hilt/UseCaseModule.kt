package com.shaxpeare.albums.hilt

import com.shaxpeare.albums.data.mapper.UserMapper
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
import javax.inject.Singleton

/**
 * Module to provide all UseCases related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

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
