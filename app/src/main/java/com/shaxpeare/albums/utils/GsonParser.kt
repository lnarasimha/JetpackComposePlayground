package com.shaxpeare.albums.utils

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser constructor(
    private val gson: Gson
) : JsonParser {

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }
}
