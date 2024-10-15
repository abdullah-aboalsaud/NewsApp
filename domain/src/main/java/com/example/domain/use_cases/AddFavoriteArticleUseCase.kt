package com.example.domain.use_cases

import com.example.domain.models.news.Article
import com.example.domain.repositories.FavoriteRepo
import javax.inject.Inject

class AddFavoriteArticleUseCase @Inject constructor(
    private val favoriteRepo: FavoriteRepo
) {
    suspend fun invoke(article: Article){
        favoriteRepo.addArticle(article)
    }
}