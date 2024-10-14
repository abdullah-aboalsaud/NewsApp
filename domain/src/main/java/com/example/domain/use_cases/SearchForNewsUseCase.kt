package com.example.domain.use_cases

import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo
import com.example.domain.utils.Resource
import javax.inject.Inject

class SearchForNewsUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(searchQuery: String): Resource<List<Article>?> {
        return newsRepo.searchForNews(searchQuery)
    }
}