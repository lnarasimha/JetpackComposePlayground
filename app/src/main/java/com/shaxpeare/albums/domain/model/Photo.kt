package com.shaxpeare.albums.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

//https://jsonplaceholder.typicode.com/albums/1/photos?_page=1&_limit=20

@Parcelize
@Entity(tableName = "photo_table")
data class Photo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable
