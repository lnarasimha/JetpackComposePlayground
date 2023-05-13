package com.shaxpeare.albums.data.model

//https://jsonplaceholder.typicode.com/albums/1/photos?_page=1&_limit=20

data class ApiPhoto(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
