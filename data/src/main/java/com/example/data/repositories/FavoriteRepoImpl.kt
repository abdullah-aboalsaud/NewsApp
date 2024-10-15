package com.example.data.repositories

import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntityDM
import com.example.data.repositories.data_sources.local_data_source.FavoriteLocalDataSource
import com.example.domain.models.news.Article
import com.example.domain.repositories.FavoriteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
) : FavoriteRepo {

    override suspend fun getAllFavoriteArticles(): Flow<List<Article>> {
        return favoriteLocalDataSource.getAllFavoriteArticles().map { articlesList ->
            articlesList.map { articleEntity ->
                articleEntity.toDomain()
            }
        }

    }

    override suspend fun addArticle(article: Article) {
        favoriteLocalDataSource.addArticle(article.toEntityDM())
    }

    override suspend fun deleteArticle(article: Article) {
        favoriteLocalDataSource.deleteArticle(article.toEntityDM())
    }

}