package ru.itis.homework7.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.homework7.BuildConfig

fun Retrofit.Builder.client(interceptors: List<Interceptor>? = null): Retrofit.Builder {
    return this.client(
        OkHttpClient.Builder().apply {
            interceptors?.forEach {
                addInterceptor(it)
            }
        }.build()
    )
}

fun builder(baseUrl: String = BuildConfig.FIXER_BASE_URL): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
}