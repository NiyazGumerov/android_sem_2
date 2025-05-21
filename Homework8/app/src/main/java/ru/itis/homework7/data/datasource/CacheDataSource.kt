package ru.itis.homework7.data.datasource

import ru.itis.homework7.data.network.model.RatesResponse

interface CacheDataSource {
    suspend fun getCourseFromCache(date: String): RatesResponse?
    suspend fun saveCourseToCache(ratesResponse: RatesResponse, date: String)
}