package com.example.domain.repositories


import com.example.domain.models.news.Article


interface NewsRepo {
    suspend fun getHeadLines(language: String): List<Article>?
    suspend fun getArticlesBySourceId(sourceId: String): List<Article>?
}