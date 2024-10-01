package com.example.domain.use_cases

import com.example.domain.models.headlines.Source
import com.example.domain.repositories.NewsRepo
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(category: String): List<Source>? {
        return newsRepo.getSources(category)
    }
}