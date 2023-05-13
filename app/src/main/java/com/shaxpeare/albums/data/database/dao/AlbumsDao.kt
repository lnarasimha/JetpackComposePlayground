package com.shaxpeare.albums.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaxpeare.albums.domain.model.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM album_table ORDER BY id ASC")
    fun getAlbums(): PagingSource<Int, Album>

    @Query("SELECT * FROM album_table WHERE id=:albumId")
    fun getSelectedAlbum(albumId: Int): Album

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbums(albums: List<Album>)

    @Query("DELETE FROM album_table")
    suspend fun deleteAllAlbums()
}
