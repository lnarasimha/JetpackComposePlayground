package com.shaxpeare.albums.data.database.dao

import androidx.test.core.app.ApplicationProvider
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.domain.model.Album
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class AlbumsDaoTest {

    lateinit var albumsDatabase: AlbumsDatabase
    lateinit var albumsDao: AlbumsDao

    @Before
    fun setUp() {
        albumsDatabase = AlbumsDatabase.create(
            ApplicationProvider.getApplicationContext(),
            true
        )
        albumsDao = albumsDatabase.albumsDao()
    }

    @Test
    fun insert_Valid_Albums_List_Returns_Success() = runBlocking {
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
    fun query_Album_Return_Appropriate_Album_Object() = runBlocking {
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
    fun delete_Albums_Returns_Success() = runBlocking {
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
