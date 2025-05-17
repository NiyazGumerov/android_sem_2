package ru.itis.homework7.domain

import ru.itis.homework7.data.Effect
import ru.itis.homework7.data.map
import ru.itis.homework7.domain.mapper.toRates
import ru.itis.homework7.domain.model.Rates
import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Inject

class GetActualCourseUseCaseImpl @Inject constructor(
    private val repository: CourseRepository
) : GetActualCourseUseCase {

    override suspend fun getActualCourse(): ru.itis.homework7.data.Effect<Rates> {
        return repository.getActualCourse().map { it.toRates() }
    }
}

interface GetActualCourseUseCase {
    suspend fun getActualCourse(): ru.itis.homework7.data.Effect<Rates>
}