package ru.itis.homework7.data.datasource

import ru.itis.homework7.data.datasource.model.Cache
import ru.itis.homework7.data.network.model.RatesResponse
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor() : CacheDataSource {

    val mutableList = mutableListOf<Cache?>(null, null, null)

    override suspend fun getCourseFromCache(date: String): RatesResponse? {
        val cache = mutableList.firstOrNull { it?.date == date }

        if (cache != null && Calendar.getInstance().timeInMillis - cache.calendar.timeInMillis < TimeUnit.MINUTES.toMillis(5) ) {
            return cache.ratesResponse
        }

        return null
    }

    override suspend fun saveCourseToCache(ratesResponse: RatesResponse, date: String) {
        val cache = Cache(
            calendar = Calendar.getInstance(),
            ratesResponse = ratesResponse,
            date = date
        )

        mutableList.add(cache)
        if (mutableList.size > 3) {
            mutableList.removeAt(0)
        }
    }
}