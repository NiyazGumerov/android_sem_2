package ru.itis.homework7.domain.mapper

import ru.itis.homework7.data.network.model.FixerError
import ru.itis.homework7.data.network.model.RatesResponse
import ru.itis.homework7.domain.model.Rates

fun RatesResponse.toRates(): Rates {
    return Rates(
        success = success,
        base = base,
        date = date,
        rates = rates,
        error = when (error) {
            is FixerError -> Rates.FixerError(error.code)
            else -> null
        }
    )
}