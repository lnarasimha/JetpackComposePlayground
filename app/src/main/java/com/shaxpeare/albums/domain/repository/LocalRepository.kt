package com.shaxpeare.albums.domain.repository

import com.shaxpeare.albums.domain.model.User

/**
 * Contract for Local Repository.
 */
interface LocalRepository {
    suspend fun saveUsers(users: List<User>): List<Long>
    suspend fun getAllUsers(): List<User>
}
