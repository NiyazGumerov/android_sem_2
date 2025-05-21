package ru.itis.homework7.data.network.api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itis.homework7.BuildConfig
import ru.itis.homework7.data.network.model.RatesResponse

interface FixerApi {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String = BuildConfig.fixerApiKey,
        @Query("symbols") symbols: String? = null
    ): Response<RatesResponse>

    @GET("{date}")
    suspend fun getHistoricalRates(
        @Path("date") date: String,
        @Query("access_key") accessKey: String = BuildConfig.fixerApiKey,
        @Query("symbols") symbols: String? = null
    ): Response<RatesResponse>
}