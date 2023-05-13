package com.shaxpeare.albums.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shaxpeare.albums.data.mapper.AlbumMapper
import com.shaxpeare.albums.data.mapper.PhotoMapper
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.system.measureTimeMillis

/**
 * Starting page index for making the first call.
 */
private const val STARTING_PAGE_INDEX = 1

class AlbumsPagingSource @Inject constructor(
    private val albumsService: AlbumsService,
    private val albumMapper: AlbumMapper,
    private val photoMapper: PhotoMapper
) : PagingSource<Int, Album>() {

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            Log.e("PAGING", "Fetching for position $position")

            val albums = withContext(Dispatchers.IO) {
                val albums = albumsService.getAlbumsFromPaging(position)
                    .map { albumMapper.toDomain(it) }
                    .map { async { fetchItemData(it) } }
                    .awaitAll()

                albums
            }

            LoadResult.Page(
                data = albums as List<Album>,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (albums.isNullOrEmpty()) null else position + 1
            )

//            val mappedAlbums = withContext(Dispatchers.IO){
//                val mappedAlbums = albums.map { albumMapper.toDomain(it) }
//
//                 mappedAlbums.forEach { album ->
//                     val photo =
//                         withContext(Dispatchers.Default) {
//                             albumsService.getAlbumPhotos(album.id)
//                         }
//                     album.photos = photoMapper.toDomain(photo)
////                    val photos = albumsService.getAlbumPhotos(album.id).map { photoMapper.toDomain(it) }
////                    album.photos= photos
//                }
//                mappedAlbums
//            }

//            LoadResult.Page(
//                data = if (mappedAlbums != null) mappedAlbums as List<Album> else emptyList(),
//                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = if (albums.isNullOrEmpty()) null else position + 1
//            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun fetchItemData(item: Album) : Album{
        val response = albumsService.getAlbumPhotos(item.id)
        if (response.isNotEmpty()) {
            item.photos = photoMapper.toDomain(response)
        }
        return item
    }
}
