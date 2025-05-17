package ru.itis.homework7.domain.repository

import ru.itis.homework7.data.network.Effect
import ru.itis.homework7.data.network.model.RatesResponse
import ru.itis.homework7.domain.model.Rates

interface CourseRepository {
    suspend fun getActualCourse(): Effect<RatesResponse>
    suspend fun getCourseByDate(date: String): Effect<RatesResponse>
}