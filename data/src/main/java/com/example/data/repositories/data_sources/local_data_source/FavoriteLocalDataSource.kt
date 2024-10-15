package com.example.data.repositories.data_sources.local_data_source

import com.example.data.room.ArticleEntityDM
import com.example.data.room.ArticlesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// this class was created to be a template to use later
class FavoriteLocalDataSource @Inject constructor(
    private val articlesDao: ArticlesDao
) {

    fun getAllFavoriteArticles(): Flow<List<ArticleEntityDM>> {
        return articlesDao.getAllFavoriteArticles()
    }

    suspend fun addArticle(article: ArticleEntityDM) {
        articlesDao.addArticle(article)
    }

    suspend fun deleteArticle(article: ArticleEntityDM) {
        articlesDao.deleteArticle(article)
    }

}