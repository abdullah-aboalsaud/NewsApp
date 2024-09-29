package com.example.domain.models.headlines

import com.example.domain.models.news.Article

data class HeadlineResponse(
    val totalResults: Int? = null,
    val articles: List<Article?>? = null,
    val status: String? = null
)
