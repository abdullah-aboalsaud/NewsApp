package com.example.data.api.models.headlines

import com.example.data.api.models.news.ArticleDM

data class HeadlineResponseDM(
    val totalResults: Int? = null,
    val articles: List<ArticleDM?>? = null,
    val status: String? = null
)
