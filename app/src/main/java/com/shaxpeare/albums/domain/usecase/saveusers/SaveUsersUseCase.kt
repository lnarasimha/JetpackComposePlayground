package com.shaxpeare.albums.domain.usecase.saveusers

import com.shaxpeare.albums.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface SaveUsersUseCase {
    operator fun invoke() : Flow<Resource<List<Long>>>
}
