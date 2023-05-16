package com.shaxpeare.albums.domain.usecase.getusers

import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.model.User
import com.shaxpeare.albums.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Get Users Use Case Contract Implementation.
 */
class GetUsersUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : GetUsersUseCase {

    override operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading)
            emit(Resource.Success(localRepository.getAllUsers()))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage.orEmpty()))
        }
    }
}
