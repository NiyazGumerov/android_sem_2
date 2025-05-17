package ru.itis.homework7.auth.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.auth.data.repository.UserRepositoryImpl
import ru.itis.homework7.auth.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserRepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository

}