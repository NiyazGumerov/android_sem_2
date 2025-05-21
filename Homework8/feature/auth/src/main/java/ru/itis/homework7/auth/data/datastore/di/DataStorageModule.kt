package ru.itis.homework7.auth.data.datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.auth.data.datastore.UserDataStorage
import ru.itis.homework7.auth.data.datastore.UserDataStorageImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStorageModule {

    @Binds
    @Singleton
    fun provideUserDataStorage(impl: UserDataStorageImpl): UserDataStorage

}