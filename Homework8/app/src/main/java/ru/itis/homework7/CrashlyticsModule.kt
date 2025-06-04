package ru.itis.homework7

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.data.Crashlytics
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CrashlyticsModule {

    @Binds
    @Singleton
    fun provideCrashlytics(impl: CrashlyticsImpl): Crashlytics

}