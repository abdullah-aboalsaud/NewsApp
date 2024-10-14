package com.example.domain.use_cases

import com.example.domain.models.headlines.Source
import com.example.domain.repositories.NewsRepo
import com.example.domain.utils.Resource
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(category: String): Resource<List<Source>?> {
        return newsRepo.getSources(category)
    }

}