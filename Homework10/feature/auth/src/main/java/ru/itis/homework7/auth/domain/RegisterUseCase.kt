package ru.itis.homework7.auth.domain

import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.auth.domain.repository.UserRepository
import ru.itis.homework7.data.Effect
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : RegisterUseCase {

    override suspend fun register(user: User): Effect<Unit> {
        return repository.register(user)
    }
}

interface RegisterUseCase {
    suspend fun register(user: User): Effect<Unit>
}