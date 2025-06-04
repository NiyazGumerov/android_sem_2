package ru.itis.homework7.domain.repository

import ru.itis.homework7.data.Effect
import ru.itis.homework7.data.repository.model.RatesData

interface CourseRepository {
    suspend fun getActualCourse(): Effect<RatesData>
    suspend fun getCourseByDate(date: String): Effect<RatesData>
    suspend fun getUserId(): Effect<Boolean>
}