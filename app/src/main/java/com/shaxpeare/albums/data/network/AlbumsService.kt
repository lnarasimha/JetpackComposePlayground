package com.shaxpeare.albums.data.network

import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.data.model.ApiUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit Interface describing API calls.
 */
interface AlbumsService {
    @GET("albums/?_limit=5")
    suspend fun getAlbumsFromPaging(
        @Query("_page") page: Int,
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
