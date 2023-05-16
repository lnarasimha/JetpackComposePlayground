package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.domain.model.Photo
import javax.inject.Inject

/**
 * Mapper to convert [ApiPhoto] to [Photo]
 */
class PhotoMapper @Inject constructor() : Mapper<ApiPhoto, Photo> {

    override fun toDomain(from: ApiPhoto): Photo {
        require(from.id > 0)
        require(from.albumId > 0)
        return Photo(
            from.id,
            from.albumId,
            from.title,
            from.url,
            from.thumbnailUrl
        )
    }
}
