package ru.itis.homework7.auth.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.homework7.auth.data.database.dao.UserDAO
import ru.itis.homework7.auth.data.database.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class InceptionDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}