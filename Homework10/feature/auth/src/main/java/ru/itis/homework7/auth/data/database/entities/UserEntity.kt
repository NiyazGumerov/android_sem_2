package ru.itis.homework7.auth.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "hashed_password")
    val hashedPassword: String
)