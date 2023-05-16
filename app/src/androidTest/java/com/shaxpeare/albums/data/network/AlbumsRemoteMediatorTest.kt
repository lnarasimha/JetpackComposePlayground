package com.shaxpeare.albums.data.network

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.data.mapper.UserMapper
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.hilt.IoDispatcher
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class AlbumsRemoteMediatorTest {

    private lateinit var albumsService: FakeAlbumsService
    private lateinit var albumsDatabase: AlbumsDatabase
    private lateinit var albumMapper: AlbumMapper
    private lateinit var photoMapper: PhotoMapper
    private lateinit var userMapper: UserMapper

    @IoDispatcher
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var scheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        scheduler = TestCoroutineScheduler()
        dispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(dispatcher)
        albumsService = FakeAlbumsService()
        albumsDatabase = AlbumsDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
        albumMapper = AlbumMapper()
        photoMapper = PhotoMapper()
        userMapper = UserMapper()
    }

    @After
    fun tearDown() {
        albumsDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runTest {
            // Given
            val remoteMediator = getAlbumsRemoteMediator()
            remoteMediator.initialize()
            val pagingState = getPagingState()

            // When
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            scheduler.advanceUntilIdle()

            // Then
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Success)
            TestCase.assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runTest {
            // Given
            albumsService.clearData()
            val remoteMediator = getAlbumsRemoteMediator()
            remoteMediator.initialize()
            val pagingState = getPagingState()

            // When
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            scheduler.advanceUntilIdle()

            // Then
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Success)
            TestCase.assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runTest {
            // Given
            albumsService.addException()
            val remoteMediator = getAlbumsRemoteMediator()
            remoteMediator.initialize()
            val pagingState = getPagingState()

            // When
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            scheduler.advanceUntilIdle()

            // Then
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Error)
        }

    private fun getAlbumsRemoteMediator() = AlbumsRemoteMediator(
        albumsService,
        albumsDatabase,
        albumMapper,
        photoMapper,
        userMapper,
        dispatcher
    )

    private fun getPagingState() = PagingState<Int, Album>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(pageSize = 3),
        leadingPlaceholderCount = 0
    )
}
