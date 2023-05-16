package com.shaxpeare.albums.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaxpeare.albums.domain.model.AlbumsRemoteKey

/**
 * Data Access Object for AlbumsRemoteKeys in Room DB.
 * Used in Paging 3 Library for fetching items from server.
 */
@Dao
interface AlbumsRemoteKeysDao {
    @Query("SELECT * FROM albums_remote_keys_table WHERE id = :albumId")
    suspend fun getRemoteKeys(albumId: Int): AlbumsRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(albumRemoteKeys: List<AlbumsRemoteKey>)

    @Query("DELETE FROM albums_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}
