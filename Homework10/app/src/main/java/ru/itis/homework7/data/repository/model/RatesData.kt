package ru.itis.homework7.data.repository.model

import ru.itis.homework7.data.network.model.RatesResponse

data class RatesData(
    val ratesResponse: RatesResponse,
    val source: Source
) {
    enum class Source {
        NETWORK,
        CACHE
    }
}