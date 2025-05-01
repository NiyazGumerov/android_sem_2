package ru.itis.homework7.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.itis.homework7.domain.GetActualCourseUseCase
import ru.itis.homework7.domain.GetActualCourseUseCaseImpl
import ru.itis.homework7.domain.GetCourseByDateUseCase
import ru.itis.homework7.domain.GetCourseByDateUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun provideGetActualCourseUseCase(impl: GetActualCourseUseCaseImpl): GetActualCourseUseCase

    @Binds
    @ViewModelScoped
    fun provideGetCourseByDateUseCase(impl: GetCourseByDateUseCaseImpl): GetCourseByDateUseCase
}