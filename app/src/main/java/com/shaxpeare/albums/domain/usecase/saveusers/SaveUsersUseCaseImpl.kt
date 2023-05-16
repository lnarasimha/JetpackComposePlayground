package com.shaxpeare.albums.domain.usecase.saveusers

import com.shaxpeare.albums.data.mapper.UserMapper
import com.shaxpeare.albums.domain.model.Resource
import com.shaxpeare.albums.domain.repository.AlbumsRepository
import com.shaxpeare.albums.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Save Users Use Case Contract Implementation.
 */
class SaveUsersUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val albumsRepository: AlbumsRepository,
    private val usersMapper: UserMapper
) : SaveUsersUseCase {

     override operator fun invoke(): Flow<Resource<List<Long>>> = flow {
        try {
            emit(Resource.Loading)
            val savedUsers = localRepository.getAllUsers()
            if (savedUsers.isNotEmpty()) {
                val longList = savedUsers.map { it.id.toLong() }
                emit(Resource.Success(longList))
            } else {
                val users = albumsRepository.getUsers()
                val mappedUsers = usersMapper.toDomain(users)
                val savedUsersList = localRepository.saveUsers(mappedUsers)
                emit(Resource.Success(savedUsersList))
            }
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage.orEmpty()))
        }
    }
}
