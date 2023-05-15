package com.shaxpeare.albums.data.network

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shaxpeare.albums.data.database.AlbumsDatabase
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.AlbumsRemoteKey
import com.shaxpeare.albums.domain.model.User
import com.shaxpeare.albums.hilt.IoDispatcher
import kotlinx.coroutines.*

/**
 * Starting page index for making the first call.
 */
private const val STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class AlbumsRemoteMediator constructor(
    private val albumsService: AlbumsService,
    private val albumsDatabase: AlbumsDatabase,
    private val albumMapper: AlbumMapper,
    private val photoMapper: PhotoMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : RemoteMediator<Int, Album>() {

    private lateinit var users: List<User>

    private val albumsDao = albumsDatabase.albumsDao()
    private val albumsRemoteKeysDao = albumsDatabase.albumsRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        initialiseUsersList()
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Album>): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: STARTING_PAGE_INDEX
                }

                LoadType.PREPEND -> {

                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    Log.e("PAGING", " PREPEND - $prevPage")
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    Log.e("PAGING", "APPEND - $nextPage")
                    nextPage
                }
            }

            val albumsResponse = albumsService.getAlbumsFromPaging(page)
            if (albumsResponse.isNotEmpty()) {
                val albums = withContext(dispatcher) {
                    albumsResponse
                        .map { albumMapper.toDomain(it) }
                        .map { async { fetchItemData(it) } }
                        .awaitAll()
                        .map { album ->
                            album.apply {
                                userName = users.firstOrNull { userId == it.id }?.name ?: ""
                            }
                        }
                }

                albumsDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        albumsDao.deleteAllAlbums()
                        albumsRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevPage = if (page > 1) page - 1 else null
                    val nextPage = if (albums.isEmpty()) null else page + 1

                    val keys = albums.map { album ->
                        AlbumsRemoteKey(
                            id = album.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                        )
                    }
                    albumsRemoteKeysDao.addAllRemoteKeys(keys)
                    albumsDao.addAlbums(albums)
                }
            }

            MediatorResult.Success(endOfPaginationReached = albumsResponse.isEmpty())
        } catch (throwable: Exception) {
            MediatorResult.Error(throwable)
        }
    }

    private suspend fun fetchItemData(item: Album): Album {
        val response = albumsService.getAlbumPhotos(item.id)
        if (response.isNotEmpty()) {
            item.photos = photoMapper.toDomain(response)
        }
        return item
    }

    private suspend fun initialiseUsersList() {
        withContext(dispatcher) {
            if (!this@AlbumsRemoteMediator::users.isInitialized) {
                users = albumsDatabase.usersDao().getAllUsers()
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Album>
    ): AlbumsRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                albumsRemoteKeysDao.getRemoteKeys(albumId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Album>
    ): AlbumsRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                albumsRemoteKeysDao.getRemoteKeys(albumId = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Album>
    ): AlbumsRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                albumsRemoteKeysDao.getRemoteKeys(albumId = hero.id)
            }
    }
}
