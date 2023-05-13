package com.shaxpeare.albums.data.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shaxpeare.albums.domain.model.Photo
import com.shaxpeare.albums.utils.JsonParser

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
