package com.shaxpeare.albums.data.database.dao

import androidx.test.core.app.ApplicationProvider
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_1_DOMAIN
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_2_DOMAIN
import com.shaxpeare.albums.TestData.USER_ITEM_VALID_3_DOMAIN
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class UsersDaoTest {

    lateinit var albumsDatabase: AlbumsDatabase
    lateinit var usersDao: UsersDao

    @Before
    fun setUp() {
        albumsDatabase = AlbumsDatabase.create(
            ApplicationProvider.getApplicationContext(),
            true
        )
        usersDao = albumsDatabase.usersDao()
    }

    @Test
    fun insert_Valid_Users_List_Returns_Success() = runBlocking{
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
    fun query_Album_Return_Appropriate_Album_Object() = runBlocking {
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
        Assert.assertEquals(result.name,"Leanne Graham" )
        Assert.assertEquals(result.username, "Bret")
    }

    @Test
    fun delete_All_Users_Returns_Success() = runBlocking {
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
