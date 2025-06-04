package ru.itis.homework7.auth.data.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.homework7.auth.data.database.InceptionDatabase
import ru.itis.homework7.auth.data.database.dao.UserDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "InceptionDB"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): InceptionDatabase {
        return Room.databaseBuilder(context, InceptionDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideDAO(database: InceptionDatabase): UserDAO {
        return database.userDAO
    }
}

