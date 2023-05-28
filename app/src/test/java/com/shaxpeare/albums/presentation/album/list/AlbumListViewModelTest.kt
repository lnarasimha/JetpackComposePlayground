package com.shaxpeare.albums.presentation.album.list

import androidx.paging.Pager
import androidx.paging.PagingData
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.usecase.getalbums.GetAlbumsUseCase
import com.shaxpeare.albums.presentation.BaseViewModelTest
import com.shaxpeare.albums.utils.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListViewModelTest : BaseViewModelTest(){

    @Mock
    lateinit var getAlbumsUseCase: GetAlbumsUseCase
    @Mock
    lateinit var pager: Pager<Int, Album>


    @Test
    fun `test get albums use case works correctly`() = runTest {

        // Given
        Mockito.`when`(getAlbumsUseCase.getAlbumsWithPaging()).thenReturn(
            flow {
                emit(PagingData.from(listOf(
                    TestData.ALBUM_ITEM_VALID_1_DOMAIN
                )))
            }
        )
        Mockito.`when`(pager.flow).thenReturn(
            flow {
                emit(PagingData.from(listOf(
                    TestData.ALBUM_ITEM_VALID_1_DOMAIN
                )))
            }
        )
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        val albumListViewModel = AlbumListViewModel(pager)
        val result = albumListViewModel.getAlbums().first()

        // Then
        assert(result is PagingData<Album>)
    }
}
