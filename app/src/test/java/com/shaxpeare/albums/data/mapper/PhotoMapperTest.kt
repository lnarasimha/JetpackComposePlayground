package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.TestData
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.domain.model.Photo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

open class BasePhotoMapperTest() {

    lateinit var mapper: PhotoMapper

    @Before
    fun setup() {
        mapper = PhotoMapper()
    }
}

@RunWith(value = Parameterized::class)
internal class PhotoMapperTest(
    private val input: ApiPhoto,
    private val outPut: Photo
) : BasePhotoMapperTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "test {0} is mapped to {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(TestData.PHOTO_ITEM_VALID_1, TestData.PHOTO_ITEM_VALID_1_DOMAIN),
                arrayOf(TestData.PHOTO_ITEM_VALID_2, TestData.PHOTO_ITEM_VALID_2_DOMAIN),
                arrayOf(TestData.PHOTO_ITEM_VALID_3, TestData.PHOTO_ITEM_VALID_3_DOMAIN),
                arrayOf(TestData.PHOTO_ITEM_VALID_4, TestData.PHOTO_ITEM_VALID_4_DOMAIN)
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

internal class ExceptionsPhotosMapperTest : BasePhotoMapperTest() {

    @Test(expected = IllegalArgumentException::class)
    fun `test  IllegalArgumentException thrown for an invalid data model`() {
        mapper.toDomain(TestData.PHOTO_ITEM_INVALID_1)

        // Expect Exception
    }
}
