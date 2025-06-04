package ru.itis.homework7.data.network.model

import com.google.gson.annotations.SerializedName

data class RatesResponse(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("base")
    val base: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("rates")
    val rates: Map<String, Double>?,
    @SerializedName("error")
    val error: FixerError? = null
)
data class FixerError(
    @SerializedName("code")
    val code: Int,
)