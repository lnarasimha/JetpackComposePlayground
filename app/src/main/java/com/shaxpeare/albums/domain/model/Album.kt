package com.shaxpeare.albums.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shaxpeare.albums.data.database.converters.Converters
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Entity(tableName = "album_table")
data class Album(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId: Int,
    val title: String,
    var photos: List<Photo> = emptyList(),
    var userName: String = ""
) : Parcelable
