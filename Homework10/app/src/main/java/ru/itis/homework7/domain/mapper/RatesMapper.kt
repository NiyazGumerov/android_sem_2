package ru.itis.homework7.domain.mapper

import ru.itis.homework7.data.network.model.FixerError
import ru.itis.homework7.data.network.model.RatesResponse
import ru.itis.homework7.data.repository.model.RatesData
import ru.itis.homework7.domain.model.Rates

fun RatesData.toRates(): Rates {
    return Rates(
        success = ratesResponse.success,
        base = ratesResponse.base,
        date = ratesResponse.date,
        rates = ratesResponse.rates,
        source = when (source) {
            RatesData.Source.CACHE -> Rates.Source.CACHE
            else -> Rates.Source.NETWORK
        },
        error = when (ratesResponse.error) {
            is FixerError -> Rates.FixerError(ratesResponse.error.code)
            else -> null
        }
    )
}