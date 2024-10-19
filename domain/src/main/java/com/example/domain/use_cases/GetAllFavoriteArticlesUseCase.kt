package com.example.domain.use_cases

import com.example.domain.models.news.Article
import com.example.domain.repositories.FavoriteRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteArticlesUseCase @Inject constructor(
    private val favoriteRepo: FavoriteRepo
){
    suspend fun invoke(): Flow<List<Article>> {
       return favoriteRepo.getAllFavoriteArticles()
    }
}