package com.example.domain.repositories


import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.utils.Result


interface NewsRepo {
    suspend fun getHeadLines(category: String): List<Article>?
    suspend fun getSources(category: String): List<Source>?

}