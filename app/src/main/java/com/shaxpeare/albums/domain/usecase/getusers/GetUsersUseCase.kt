package com.shaxpeare.albums.domain.usecase.getusers

import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Get Users Use Case Contract.
 */
interface GetUsersUseCase {
    operator fun invoke() : Flow<Resource<List<User>>>
}
