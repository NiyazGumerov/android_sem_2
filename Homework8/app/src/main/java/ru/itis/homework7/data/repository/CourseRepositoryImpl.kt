package ru.itis.homework7.data.repository

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import ru.itis.homework7.data.datasource.CacheDataSource
import ru.itis.homework7.data.Effect
import ru.itis.homework7.data.Success
import ru.itis.homework7.data.network.api.FixerApi
import ru.itis.homework7.data.call
import ru.itis.homework7.data.network.di.IoDispatcher
import ru.itis.homework7.data.doOnSuccess
import ru.itis.homework7.data.map
import ru.itis.homework7.data.repository.model.RatesData
import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Inject

const val USER_ID_KEY = "USER_ID"
private const val RATE_SYMBOLS =
    "USD, JPY, GBP, AUD, CAD, CHF, CNY, NZD, SEK, NOK, MXN, SGD, HKD, KRW, INR, RUB, ZAR, TRY, BRL, DKK"

class CourseRepositoryImpl @Inject constructor(
    private val userSharedPreferences: SharedPreferences,
    private val api: FixerApi,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val cacheDataSource: CacheDataSource,
) : CourseRepository {

    override suspend fun getActualCourse(): ru.itis.homework7.data.Effect<RatesData> {
        return ru.itis.homework7.data.call(dispatcher) {
            delay(2000)
            api.getLatestRates(symbols = RATE_SYMBOLS)
        }.map { RatesData(it, RatesData.Source.NETWORK) }
    }

    override suspend fun getCourseByDate(date: String): ru.itis.homework7.data.Effect<RatesData> {
        val cache = cacheDataSource.getCourseFromCache(date)
        if (cache != null) {
            return ru.itis.homework7.data.Success(RatesData(cache, RatesData.Source.CACHE))
        }
        return ru.itis.homework7.data.call(dispatcher) {
            delay(2000)

            api.getHistoricalRates(
                date = date,
                symbols = RATE_SYMBOLS
            )
        }.doOnSuccess { cacheDataSource.saveCourseToCache(it, date) }
            .map { RatesData(it, RatesData.Source.NETWORK) }
    }

    override suspend fun getUserId(): Effect<Boolean> {
        return Success(userSharedPreferences.contains(USER_ID_KEY))
    }
}