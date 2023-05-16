package com.shaxpeare.albums.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_remote_keys_table")
data class AlbumsRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long
)
