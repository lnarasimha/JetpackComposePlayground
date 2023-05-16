package com.shaxpeare.albums.data.network

import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_1
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_10
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_11
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_12
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_13
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_14
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_15
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_16
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_17
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_2
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_3
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_4
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_5
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_6
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_7
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_8
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_9
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_1
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_10
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_11
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_12
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_13
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_14
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_2
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_3
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_4
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_5
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_6
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_7
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_8
import com.shaxpeare.albums.TestData.PHOTO_ITEM_VALID_9
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_1
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_10
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_2
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_3
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_4
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_5
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_6
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_7
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_9
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.User
import java.io.IOException


class FakeAlbumsService : AlbumsService {

    private val albums: Map<Int, List<ApiAlbum>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4
        )
    }

    private val photos: Map<Int, List<ApiPhoto>> by lazy {
        mapOf(
            1 to photoPage1,
            11 to photoPage2,
            21 to photoPage3,
            22 to photoPage4,
            23 to photoPage5,
            6 to photoPage6,
            7 to photoPage7
        )
    }

    private val users = listOf<ApiUser>(
        USER_ITEM_VALID_1,
        USER_ITEM_VALID_2,
        USER_ITEM_VALID_3,
        USER_ITEM_VALID_4,
        USER_ITEM_VALID_5,
        USER_ITEM_VALID_6,
        USER_ITEM_VALID_7,
        USER_ITEM_VALID_9,
        USER_ITEM_VALID_10,
    )

    private var photoPage1 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_1,
        PHOTO_ITEM_VALID_2
    )

    private var photoPage2 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_3,
        PHOTO_ITEM_VALID_4
    )

    private var photoPage3 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_5,
        PHOTO_ITEM_VALID_6
    )

    private var photoPage4 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_7,
        PHOTO_ITEM_VALID_8
    )

    private var photoPage5 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_9,
        PHOTO_ITEM_VALID_10
    )

    private var photoPage6 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_11,
        PHOTO_ITEM_VALID_12
    )

    private var photoPage7 = listOf<ApiPhoto>(
        PHOTO_ITEM_VALID_13,
        PHOTO_ITEM_VALID_14
    )

    private var page1 = listOf<ApiAlbum>(
        ALBUM_ITEM_VALID_1,
        ALBUM_ITEM_VALID_2,
        ALBUM_ITEM_VALID_3,
        ALBUM_ITEM_VALID_4,
        ALBUM_ITEM_VALID_5
    )

    private var page2 = listOf<ApiAlbum>(
        ALBUM_ITEM_VALID_6,
        ALBUM_ITEM_VALID_7,
        ALBUM_ITEM_VALID_8,
        ALBUM_ITEM_VALID_9,
        ALBUM_ITEM_VALID_10
    )

    private var page3 = listOf<ApiAlbum>(
        ALBUM_ITEM_VALID_11,
        ALBUM_ITEM_VALID_12,
        ALBUM_ITEM_VALID_13,
        ALBUM_ITEM_VALID_14,
        ALBUM_ITEM_VALID_15
    )

    private var page4 = listOf<ApiAlbum>(
        ALBUM_ITEM_VALID_16,
        ALBUM_ITEM_VALID_17,
    )

    private var exception = false

    fun addException() {
        exception = true
    }

    fun clearData() {
        page1 = emptyList()
    }

    override suspend fun getAlbumsFromPaging(page: Int): List<ApiAlbum> {
        if (exception) {
            throw IOException()
        }
        return albums[page]!!
    }

    override suspend fun getAllAlbums(): List<ApiAlbum> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): List<ApiUser> {
        return users
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<ApiPhoto> {
        if (exception) {
            throw IOException()
        }
        return photos[albumId]!!
    }

}
