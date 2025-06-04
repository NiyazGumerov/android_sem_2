package ru.itis.homework7.auth.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.homework7.auth.data.database.entities.UserEntity


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserData(user: UserEntity)

    @Query("SELECT * FROM users WHERE :userId = user_id")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE :username = username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

}