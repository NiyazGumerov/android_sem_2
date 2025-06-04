package ru.itis.homework7.data.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.data.datasource.CacheDataSource
import ru.itis.homework7.data.datasource.CacheDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
    @Singleton
    fun provideCache(impl: CacheDataSourceImpl): CacheDataSource
}