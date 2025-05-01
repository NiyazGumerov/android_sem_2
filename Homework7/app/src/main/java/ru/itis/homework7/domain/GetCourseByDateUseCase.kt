package ru.itis.homework7.domain

import ru.itis.homework7.data.network.Effect
import ru.itis.homework7.data.network.map
import ru.itis.homework7.domain.mapper.toRates
import ru.itis.homework7.domain.model.Rates
import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseByDateUseCaseImpl @Inject constructor(
    private val repository: CourseRepository
) : GetCourseByDateUseCase {

    override suspend fun getCourseByDate(date: String): Effect<Rates> {
        return repository.getCourseByDate(date).map { it.toRates() }
    }
}

interface GetCourseByDateUseCase {
    suspend fun getCourseByDate(date: String): Effect<Rates>
}