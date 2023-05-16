package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.User
import javax.inject.Inject

/**
 * Mapper to convert [ApiUser] to [User]
 */
class UserMapper @Inject constructor() : Mapper<ApiUser, User> {

    override fun toDomain(from: ApiUser): User {
        require(from.id > 0)
        return User(
            from.id,
            from.name,
            from.username,
            from.email,
            from.phone,
            from.website
        )
    }
}
