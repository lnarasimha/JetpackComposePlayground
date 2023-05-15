package com.shaxpeare.albums.data.network

import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.data.model.ApiUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://jsonplaceholder.typicode.com/albums/1/photos?_page=1&_limit=20

interface AlbumsService {
    @GET("albums/")
    suspend fun getAlbumsFromPaging(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10
    ): List<ApiAlbum>

    @GET("albums/")
    suspend fun getAllAlbums(): List<ApiAlbum>

    @GET("users/")
    suspend fun getUsers(): List<ApiUser>

    @GET("albums/{albumId}/photos?_page=1&_limit=2")
    suspend fun getAlbumPhotos(
        @Path("albumId") albumId: Int
    ): List<ApiPhoto>
}
