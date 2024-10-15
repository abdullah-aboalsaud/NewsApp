package com.example.data.room.di

import android.content.Context
import androidx.room.Room
import com.example.data.repositories.NewsRepoImpl
import com.example.data.repositories.data_sources.local_data_source.FavoriteLocalDataSource
import com.example.data.room.ArticlesDao
import com.example.data.room.FavoriteDatabase
import com.example.domain.repositories.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): FavoriteDatabase{
        return Room.databaseBuilder(
            applicationContext,
            FavoriteDatabase::class.java,
            FavoriteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideArticlesDao(database: FavoriteDatabase): ArticlesDao {
        return database.articleDao()
    }

    @Provides
    fun provideLocalDataSource(articlesDao: ArticlesDao): FavoriteLocalDataSource {
        return FavoriteLocalDataSource(articlesDao)
    }





}