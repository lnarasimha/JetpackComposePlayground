package com.shaxpeare.albums.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaxpeare.albums.domain.model.User

/**
 * Data Access Object for Users in Room DB.
 */
@Dao
interface UsersDao {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_table WHERE id=:userId")
    fun getSelectedUser(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<User>) : List<Long>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}
