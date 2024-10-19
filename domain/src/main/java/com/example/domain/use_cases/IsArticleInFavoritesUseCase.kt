package com.example.domain.use_cases

import com.example.domain.repositories.FavoriteRepo
import com.example.domain.repositories.NewsRepo
import javax.inject.Inject

class IsArticleInFavoritesUseCase @Inject constructor(
    private val favoriteRepo: FavoriteRepo
){
    suspend fun invoke(url: String): Boolean {
        return favoriteRepo.isArticleInFavorites(url)
    }
}