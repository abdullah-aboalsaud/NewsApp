package com.example.domain.use_cases


import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo
import javax.inject.Inject

class GetHeadLinesUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {

    suspend fun invoke(language: String): List<Article>? {
        return newsRepo.getHeadLines(language)
    }

}