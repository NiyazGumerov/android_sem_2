package ru.itis.homework7.auth.data.datastore

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.homework7.auth.data.crypto.hashPassword
import ru.itis.homework7.auth.data.database.dao.UserDAO
import ru.itis.homework7.auth.data.database.entities.UserEntity
import java.util.UUID
import javax.inject.Inject
import androidx.core.content.edit
import ru.itis.homework7.auth.data.database.di.IoDispatcher
import ru.itis.homework7.data.Crashlytics

const val USER_ID_KEY = "USER_ID"

class UserDataStorageImpl @Inject constructor(
    private val crashlytics: Crashlytics,
    private val userSharedPreferences: SharedPreferences,
    private val userDAO: UserDAO,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserDataStorage {
    override suspend fun getUserById(id: String): UserEntity? {
        return withContext(ioDispatcher) {
            userDAO.getUserById(userId = id)
                ?: throw IllegalStateException("User with given id not found")
        }
    }

    override suspend fun saveUser(username: String, password: String) {
        val userId = UUID.randomUUID().toString()
        return withContext(ioDispatcher) {
            userDAO.saveUserData(
                UserEntity(
                    id = userId,
                    username = username,
                    hashedPassword = hashPassword(password)
                )
            )
        }
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return withContext(ioDispatcher) {
            userDAO.getUserByUsername(username = username)
        }
    }

    override suspend fun saveUserIdToSharedPreferences(userId: String) {
        userSharedPreferences.edit() {
            putString(USER_ID_KEY, userId)
        }
    }
    override suspend fun setUserIdCustomKeyToCrashlytics(userId: String) {
        crashlytics.setCustomKeyToCrashlytics("USER_ID", userId)
    }
}

interface UserDataStorage {
    suspend fun getUserById(id: String): UserEntity?
    suspend fun saveUser(username: String, password: String)
    suspend fun getUserByUsername(username: String): UserEntity?
    suspend fun saveUserIdToSharedPreferences(userId: String)
    suspend fun setUserIdCustomKeyToCrashlytics(userId: String)
}