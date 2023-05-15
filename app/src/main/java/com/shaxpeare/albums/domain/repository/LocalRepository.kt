package com.shaxpeare.albums.domain.repository

import com.shaxpeare.albums.domain.model.User

interface LocalRepository {
    suspend fun saveUsers(users: List<User>): List<Long>
    suspend fun getAllUsers(): List<User>
}
