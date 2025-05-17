package ru.itis.homework7.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.data.repository.CourseRepositoryImpl
import ru.itis.homework7.domain.repository.CourseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideRepository(impl: CourseRepositoryImpl): CourseRepository
}