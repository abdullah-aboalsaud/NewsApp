package com.example.data.repositories.di

import com.example.data.repositories.FavoriteRepoImpl
import com.example.data.repositories.NewsRepoImpl
import com.example.domain.repositories.FavoriteRepo
import com.example.domain.repositories.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepo(
        newsRepoImpl: NewsRepoImpl
    ): NewsRepo

    @Binds
    @Singleton
    abstract fun bindFavoriteRepo(
        favoriteRepoImpl: FavoriteRepoImpl
    ): FavoriteRepo

}