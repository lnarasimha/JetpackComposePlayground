package com.shaxpeare.albums.hilt

import android.content.Context
import androidx.room.Room
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.database.converters.Converters
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCase
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to provide all Database related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AlbumsDatabase {
        return Room.databaseBuilder(
            context,
            AlbumsDatabase::class.java,
            "albums_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesConverter(): Converters {
        return Converters()
    }

}
