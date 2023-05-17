package com.shaxpeare.albums.data.network

import com.shaxpeare.albums.utils.FileHelper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumsServiceTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var albumsService: AlbumsService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        albumsService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumsService::class.java)
    }

    @Test
    fun testGetUsers_returnsProducts() = runTest {
        // Given
        val mockResponse = MockResponse()
        val content = FileHelper.readFile("/user_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        // When
        val response = albumsService.getUsers()
        mockWebServer.takeRequest()

        // Then
        Assert.assertEquals(false, response.isEmpty())
        Assert.assertEquals(10, response.size)
    }

    @Test
    fun testGetPhotos_returnsPhotos() = runTest {
        // Given
        val mockResponse = MockResponse()
        val content = FileHelper.readFile("/photos_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        // When
        val response = albumsService.getAlbumPhotos(1)
        mockWebServer.takeRequest()

        // Then
        Assert.assertEquals(false, response.isEmpty())
        Assert.assertEquals(2, response.size)
    }


    @Test
    fun testGetAlbums_returnsAlbums() = runTest {
        // Given
        val mockResponse = MockResponse()
        val content = FileHelper.readFile("/albums_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        // When
        val response = albumsService.getAllAlbums()
        mockWebServer.takeRequest()

        // Then
        Assert.assertEquals(false, response.isEmpty())
        Assert.assertEquals(5, response.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}
