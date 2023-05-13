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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

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
) : RemoteMediator<Int, Album>() {

    private val albumsDao = albumsDatabase.albumsDao()
    private val albumsRemoteKeysDao = albumsDatabase.albumsRemoteKeysDao()
//
//    override suspend fun initialize(): InitializeAction {
//        val currentTime = System.currentTimeMillis()
//        val lastUpdated = 0
//        val cacheTimeout = 1440
//        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
//        return if (diffInMinutes.toInt() <= cacheTimeout) {
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
//    }

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
                val albums = withContext(Dispatchers.IO) {
                    albumsResponse
                        .map { albumMapper.toDomain(it) }
                        .map { async { fetchItemData(it) } }
                        .awaitAll()
                }

                albumsDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        albumsDao.deleteAllAlbums()
                        albumsRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevPage = if (page == STARTING_PAGE_INDEX) null else page - 1
                    val nextPage = if (albums.isNullOrEmpty()) null else page + 1

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
