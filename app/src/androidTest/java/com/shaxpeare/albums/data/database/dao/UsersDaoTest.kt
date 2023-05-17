package com.shaxpeare.albums.data.database.dao

import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.utils.TestData.USER_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.domain.model.User
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
internal class UsersDaoTest : AbstractDaoTest() {

    lateinit var usersDao: UsersDao

    @Before
    override fun setUp() {
        super.setUp()
        usersDao = albumsDatabase.usersDao()
    }

    @Test
    fun insertValidUsersListReturnsSuccess() = runBlocking {
        //Given
        val users = listOf<User>(
            USER_ITEM_VALID_1_DOMAIN,
            USER_ITEM_VALID_2_DOMAIN,
            USER_ITEM_VALID_3_DOMAIN
        )
        usersDao.addUsers(users)

        // When
        val result = usersDao.getAllUsers()

        //Then
        assert(result.isNotEmpty())
        Assert.assertEquals(result.size, 3)
    }

    @Test
    fun queryAlbumReturnsAppropriateAlbumObject() = runBlocking {
        //Given
        val users = listOf<User>(
            USER_ITEM_VALID_1_DOMAIN,
            USER_ITEM_VALID_2_DOMAIN,
            USER_ITEM_VALID_3_DOMAIN
        )
        usersDao.addUsers(users)

        // When
        val result = usersDao.getSelectedUser(1)

        //Then
        Assert.assertEquals(result.id, 1)
        Assert.assertEquals(result.name, "Leanne Graham")
        Assert.assertEquals(result.username, "Bret")
    }

    @Test
    fun deleteAllUsersReturnsSuccess() = runBlocking {
        //Given
        val users = listOf<User>(
            USER_ITEM_VALID_1_DOMAIN,
            USER_ITEM_VALID_2_DOMAIN,
            USER_ITEM_VALID_3_DOMAIN
        )
        usersDao.addUsers(users)

        // When
        usersDao.deleteAllUsers()

        val result = usersDao.getAllUsers()

        // Then
        assert(result.isEmpty())
    }

    @After
    fun tearDown() {
        albumsDatabase.close()
    }
}
