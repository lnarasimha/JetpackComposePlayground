package com.shaxpeare.albums.presentation.album.list.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.Photo
import com.shaxpeare.albums.utils.setTestContent

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumListItemKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAlbumDataRendersListItemCorrectly(): Unit = with(composeTestRule) {
        val photos = listOf(
            Photo(1, 1, "First Photo", "mainUrl", "thumbnailUrl")
        )
        val album = Album(1, 1, "Photo Album", photos, "UserName")

        setTestContent {
            AlbumListItem(album = album, navigateToAlbumDetail = {})
        }

        onNodeWithText(album.userName, useUnmergedTree = true).assertIsDisplayed()
        onNodeWithText(album.title, useUnmergedTree = true).assertIsDisplayed()
        onNodeWithText(album.photos.first().title, useUnmergedTree = true).assertIsDisplayed()
    }
}
