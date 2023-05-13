package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.domain.model.Album
import javax.inject.Inject

class AlbumMapper @Inject constructor(): Mapper<ApiAlbum, Album> {

    override fun toDomain(from: ApiAlbum): Album {
        return Album(
            from.id,
            from.userId,
            from.title,
        )
    }
}
