package com.shaxpeare.albums.data.mapper

import android.util.Log
import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.domain.model.Album
import javax.inject.Inject

/**
 * Mapper to convert [ApiAlbum] to [Album]
 */
class AlbumMapper @Inject constructor() : Mapper<ApiAlbum, Album> {

    override fun toDomain(from: ApiAlbum): Album {
        require(from.id > 0)
        require(from.userId > 0)
        require(from.title.isNotEmpty())
        return Album(
            from.id,
            from.userId,
            from.title,
        )
    }
}
