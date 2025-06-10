package ru.itis.homework7.domain.model

data class Rates(
    val success: Boolean?,
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?,
    val source: Source?,
    val error: FixerError? = null
) {
    data class FixerError(
        val code: Int,
    )

    enum class Source {
        NETWORK,
        CACHE
    }
}
