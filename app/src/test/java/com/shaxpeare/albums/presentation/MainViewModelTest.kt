package com.shaxpeare.albums.presentation

import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.usecase.saveusers.SaveUsersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BaseViewModelTest(){

    @Mock
    lateinit var saveUsersUseCase: SaveUsersUseCase

    @Test
    fun `test save users use case works correctly`() = runTest {
        // Given
        Mockito.`when`(saveUsersUseCase.invoke()).thenReturn(flow {
            emit(
                Resource.Success(
                    listOf(
                        1L, 2L, 3L
                    )
                )
            )
        })
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        val mainViewModel = MainViewModel(saveUsersUseCase)
        val result = saveUsersUseCase.invoke().first()

        // Then
        assert(result is Resource.Success)
        val value = mainViewModel.fetchingData.value
        assert(value)
    }
}
