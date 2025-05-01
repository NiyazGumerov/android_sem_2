package ru.itis.homework7.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import ru.itis.homework7.data.network.Effect
import ru.itis.homework7.data.network.api.FixerApi
import ru.itis.homework7.data.network.call
import ru.itis.homework7.data.network.di.IoDispatcher
import ru.itis.homework7.data.network.model.RatesResponse
import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Inject

private const val RATE_SYMBOLS = "USD, JPY, GBP, AUD, CAD, CHF, CNY, NZD, SEK, NOK, MXN, SGD, HKD, KRW, INR, RUB, ZAR, TRY, BRL, DKK"

class CourseRepositoryImpl @Inject constructor(
    private val api: FixerApi,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : CourseRepository {

    override suspend fun getActualCourse(): Effect<RatesResponse> {
        return call(dispatcher) {
            delay(2000)
            api.getLatestRates(symbols = RATE_SYMBOLS)
        }
    }

    override suspend fun getCourseByDate(date: String): Effect<RatesResponse> {
        return call(dispatcher) {
            delay(2000)
            api.getHistoricalRates(
                date = date,
                symbols = RATE_SYMBOLS
            )
        } // TODO: Добавить выбор даты
    }

}