package ru.itis.homework7.data.datasource.model

import ru.itis.homework7.data.network.model.RatesResponse
import java.util.Calendar

data class Cache(
    val calendar: Calendar,
    val ratesResponse: RatesResponse,
    val date: String
)