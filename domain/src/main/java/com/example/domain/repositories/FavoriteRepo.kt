package com.example.domain.repositories

import com.example.domain.models.news.Article
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    suspend fun getAllFavoriteArticles(): Flow<List<Article>>
    suspend fun addArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}
