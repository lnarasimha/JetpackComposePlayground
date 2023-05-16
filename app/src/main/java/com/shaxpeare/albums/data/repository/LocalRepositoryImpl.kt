package com.shaxpeare.albums.data.repository

import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.domain.model.User
import com.shaxpeare.albums.domain.repository.LocalRepository

/**
 * Repository Contract Implementation for Local Database.
 */
class LocalRepositoryImpl(
    private val albumsDatabase: AlbumsDatabase
) : LocalRepository {

    override suspend fun saveUsers(users: List<User>): List<Long> {
        return albumsDatabase.usersDao().addUsers(users)
    }

    override suspend fun getAllUsers(): List<User> {
        return albumsDatabase.usersDao().getAllUsers()
    }
}
