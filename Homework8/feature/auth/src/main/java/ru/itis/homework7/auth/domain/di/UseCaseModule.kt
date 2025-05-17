package ru.itis.homework7.auth.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.itis.homework7.auth.domain.AuthUseCase
import ru.itis.homework7.auth.domain.AuthUseCaseImpl
import ru.itis.homework7.auth.domain.RegisterUseCase
import ru.itis.homework7.auth.domain.RegisterUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun provideAuthUseCaseImpl(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    @ViewModelScoped
    fun provideRegisterUseCaseImpl(impl: RegisterUseCaseImpl): RegisterUseCase
}