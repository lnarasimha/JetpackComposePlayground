package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.TestData.ALBUM_ITEM_INVALID_1
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_1
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_2
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_3
import com.shaxpeare.albums.TestData.ALBUM_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.domain.model.Album
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import kotlin.test.assertEquals

open class BaseAlbumMapperTest {

    lateinit var mapper: AlbumMapper

    @Before
    fun setup() {
        mapper = AlbumMapper()
    }

}

@RunWith(value = Parameterized::class)
internal class AlbumMapperTest(
    private val input: ApiAlbum,
    private val outPut: Album
) : BaseAlbumMapperTest() {

    companion object {
        @JvmStatic
        @Parameters(name = "test {0} is mapped to {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(ALBUM_ITEM_VALID_1, ALBUM_ITEM_VALID_1_DOMAIN),
                arrayOf(ALBUM_ITEM_VALID_2, ALBUM_ITEM_VALID_2_DOMAIN),
                arrayOf(ALBUM_ITEM_VALID_3, ALBUM_ITEM_VALID_3_DOMAIN)
            )
        }
    }

    @Test
    fun `test album domain model is build correctly for a valid data model`() {
        // Given Data

        // When
        val result = mapper.toDomain(input)

        //Then
        assertEquals(outPut, result)
    }

}

internal class ExceptionsAlbumMapperTest : BaseAlbumMapperTest() {

    @Test(expected = IllegalArgumentException::class)
    fun `test  IllegalArgumentException thrown for an invalid data model`() {
        mapper.toDomain(ALBUM_ITEM_INVALID_1)

        // Expect Exception
    }
}
