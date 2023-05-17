package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.utils.TestData.USER_ITEM_INVALID_1
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_1
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_2
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_3
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import kotlin.test.assertEquals

open class BaseUserMapperTest {

    lateinit var mapper: UserMapper

    @Before
    fun setup() {
        mapper = UserMapper()
    }
}

@RunWith(value = Parameterized::class)
internal class ParameterisedUserMapperTest(
    private val input: ApiUser,
    private val outPut: User
) : BaseUserMapperTest() {

    companion object {
        @JvmStatic
        @Parameters(name = "test {0} is mapped to {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(USER_ITEM_VALID_1, USER_ITEM_VALID_1_DOMAIN),
                arrayOf(USER_ITEM_VALID_2, USER_ITEM_VALID_2_DOMAIN),
                arrayOf(USER_ITEM_VALID_3, USER_ITEM_VALID_3_DOMAIN)
            )
        }
    }

    @Test
    fun `test user domain model is build correctly for a valid data model`() {
        // Given Data

        // When
        val result = mapper.toDomain(input)

        // Then
        assertEquals(outPut, result)
    }

}

@RunWith(JUnit4::class)
internal class ExceptionsUserMapperTest : BaseUserMapperTest() {

    @Test(expected = IllegalArgumentException::class)
    fun `test IllegalArgumentException thrown for an invalid data model`() {
        // Given Data

        // When
        val result = mapper.toDomain(USER_ITEM_INVALID_1)

        // Expect Exception
    }
}
