package com.example.domain.use_cases

import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo
import com.example.domain.utils.Resource
import javax.inject.Inject

class GetArticlesBySourceIdUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(sourceId: String): Resource<List<Article>?> {
       return newsRepo.getArticlesBySourceId(sourceId)
    }
}