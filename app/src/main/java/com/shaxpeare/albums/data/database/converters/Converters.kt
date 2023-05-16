package com.shaxpeare.albums.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shaxpeare.albums.domain.model.Photo

/**
 * Type converter for photos list to be stored in Room DB.
 */
class Converters(
) {

    @TypeConverter
    fun toPhotoJson(photos: List<Photo>): String {
        return Gson().toJson(photos)
    }

    @TypeConverter
    fun fromPhotosJson(json: String): List<Photo> {
        return Gson().fromJson(json, Array<Photo>::class.java).toList()
    }
}
