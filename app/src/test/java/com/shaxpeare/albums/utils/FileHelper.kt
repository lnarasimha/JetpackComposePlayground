package com.shaxpeare.albums.utils

import java.io.InputStreamReader

object FileHelper {
    fun readFile(fileName: String): String {
        val inputStream = FileHelper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}
