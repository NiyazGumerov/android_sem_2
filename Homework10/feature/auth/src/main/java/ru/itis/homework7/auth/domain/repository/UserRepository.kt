package ru.itis.homework7.auth.domain.repository

import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.data.Effect

interface UserRepository {
    suspend fun auth(user: User): Effect<Unit>
    suspend fun register(user: User): Effect<Unit>
}
