package ru.itis.homework7.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.data.network.builder
import ru.itis.homework7.data.network.client
import ru.itis.homework7.data.network.api.FixerApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCollectionApi(): FixerApi =
        builder().client().build().create(FixerApi::class.java)
}