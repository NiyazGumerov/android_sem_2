package ru.itis.homework7.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.homework7.auth.R
import ru.itis.homework7.auth.data.crypto.hashPassword
import ru.itis.homework7.auth.data.datastore.UserDataStorage
import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.auth.domain.repository.UserRepository
import ru.itis.homework7.data.Effect
import ru.itis.homework7.data.Success
import ru.itis.homework7.data.Error

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStorage: UserDataStorage,
) : UserRepository {

    override suspend fun auth(user: User): Effect<Unit> {
        if (userDataStorage.getUserByUsername(username = user.username) == null
            || userDataStorage.getUserByUsername(username = user.username)?.hashedPassword != hashPassword(
                user.password
            )
        ) {
            return Error(NullPointerException("User with given username not found"))
        } else {
            val userId = userDataStorage.getUserByUsername(username = user.username)!!.id
            userDataStorage.saveUserIdToSharedPreferences(userId = userId)
            return Success(Unit)
        }
    }

    override suspend fun register(user: User): Effect<Unit> {
        if (userDataStorage.getUserByUsername(username = user.username) == null) {
            userDataStorage.saveUser(
                username = user.username,
                password = user.password
            )
            val userId = userDataStorage.getUserByUsername(username = user.username)!!.id
            userDataStorage.saveUserIdToSharedPreferences(userId = userId)
            return Success(Unit)

        } else {
            return Error(NullPointerException("User with given username already exists"))
        }
    }
}