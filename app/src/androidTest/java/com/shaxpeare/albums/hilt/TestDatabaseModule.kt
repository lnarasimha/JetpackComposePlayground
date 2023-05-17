package com.shaxpeare.albums.hilt

import android.content.Context
import com.shaxpeare.albums.data.database.AlbumsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn


@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
class TestDatabaseModule {

    @Provides
    fun provideTestDatabaseModule(@ApplicationContext context: Context): AlbumsDatabase {
        return AlbumsDatabase.create(context, true)
    }
}
