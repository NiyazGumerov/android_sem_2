package ru.itis.homework7.auth.domain

import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.auth.domain.repository.UserRepository
import ru.itis.homework7.data.Effect
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : AuthUseCase {

    override suspend fun auth(user: User): Effect<Unit> {
        return repository.auth(user)
    }
}

interface AuthUseCase {
    suspend fun auth(user: User): Effect<Unit>
}