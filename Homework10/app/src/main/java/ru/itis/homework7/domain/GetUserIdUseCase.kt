package ru.itis.homework7.domain

import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Inject


class GetUserIdUseCaseImpl @Inject constructor(
    private val repository: CourseRepository
) : GetUserIdUseCase {

    override suspend fun getUserId(): ru.itis.homework7.data.Effect<Boolean> {
        return repository.getUserId()
    }
}

interface GetUserIdUseCase {
    suspend fun getUserId(): ru.itis.homework7.data.Effect<Boolean>
}