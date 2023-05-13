package com.shaxpeare.albums.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaxpeare.albums.domain.model.User

@Dao
interface UsersDao {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAlbums(): List<User>

    @Query("SELECT * FROM user_table WHERE id=:userId")
    fun getSelectedUser(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<User>)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}
