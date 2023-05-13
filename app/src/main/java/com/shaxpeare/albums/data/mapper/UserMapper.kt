package com.shaxpeare.albums.data.mapper

import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<ApiUser, User> {

    override fun toDomain(from: ApiUser): User {
        return User(
            from.id,
            from.name,
            from.email,
            from.phone,
            from.website
        )
    }
}
