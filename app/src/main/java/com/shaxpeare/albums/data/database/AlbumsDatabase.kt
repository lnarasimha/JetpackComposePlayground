package com.shaxpeare.albums.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaxpeare.albums.data.database.converters.Converters
import com.shaxpeare.albums.data.database.dao.AlbumsRemoteKeysDao
import com.shaxpeare.albums.data.database.dao.AlbumsDao
import com.shaxpeare.albums.data.database.dao.UsersDao
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.AlbumsRemoteKey
import com.shaxpeare.albums.domain.model.Photo
import com.shaxpeare.albums.domain.model.User

/**
 * Albums Database.
 */
@Database(
    entities = [
        Album::class,
        Photo::class,
        User::class,
        AlbumsRemoteKey::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AlbumsDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): AlbumsDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AlbumsDatabase::class.java)
            } else {
                Room.databaseBuilder(context, AlbumsDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun albumsDao(): AlbumsDao
    abstract fun usersDao(): UsersDao
    abstract fun albumsRemoteKeysDao(): AlbumsRemoteKeysDao
}
