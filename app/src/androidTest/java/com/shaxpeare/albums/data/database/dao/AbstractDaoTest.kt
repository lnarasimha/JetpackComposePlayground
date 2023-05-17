package com.shaxpeare.albums.data.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shaxpeare.albums.data.database.AlbumsDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
abstract class AbstractDaoTest {

    @get:Rule
    val instantExceutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var albumsDatabase: AlbumsDatabase


    @Before
    open fun setUp() {
        hiltAndroidRule.inject()
    }
}
