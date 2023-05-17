package com.shaxpeare.albums.data.database.dao

import com.shaxpeare.albums.utils.TestData.ALBUM_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.utils.TestData.ALBUM_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.utils.TestData.ALBUM_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.domain.model.Album
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
internal class AlbumsDaoTest : AbstractDaoTest() {

    lateinit var albumsDao: AlbumsDao

    @Before
    override fun setUp() {
        super.setUp()
        albumsDao = albumsDatabase.albumsDao()
    }

    @Test
    fun insertValidAlbumsListReturnsSuccess() = runBlocking {
        // Given
        val albums = listOf<Album>(
            ALBUM_ITEM_VALID_1_DOMAIN,
            ALBUM_ITEM_VALID_2_DOMAIN,
            ALBUM_ITEM_VALID_3_DOMAIN
        )
        albumsDao.addAlbums(albums)

        // When
        val result = albumsDao.getAllAlbums()

        //Then
        assert(result.isNotEmpty())
        Assert.assertEquals(result.size, 3)
    }


    @Test
    fun queryAlbumReturnAppropriateAlbumObject() = runBlocking {
        // Given
        val albums = listOf<Album>(
            ALBUM_ITEM_VALID_1_DOMAIN,
            ALBUM_ITEM_VALID_2_DOMAIN,
            ALBUM_ITEM_VALID_3_DOMAIN
        )
        albumsDao.addAlbums(albums)

        // When
        val result = albumsDao.getSelectedAlbum(1)

        //Then
        Assert.assertEquals(result.id, 1)
        Assert.assertEquals(result.userId, 1)
    }

    @Test
    fun deleteAlbumsReturnsSuccess() = runBlocking {
        //Given
        val albums = listOf<Album>(
            ALBUM_ITEM_VALID_1_DOMAIN,
            ALBUM_ITEM_VALID_2_DOMAIN,
            ALBUM_ITEM_VALID_3_DOMAIN
        )
        albumsDao.addAlbums(albums)

        //When
        albumsDao.deleteAllAlbums()
        val result = albumsDao.getAllAlbums()

        //Then
        assert(result.isEmpty())
    }

    @After
    fun tearDown() {
        albumsDatabase.close()
    }
}
