package com.example.domain.repositories



import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.utils.Resource


interface NewsRepo {
    suspend fun getHeadLines(category: String): Resource<List<Article>?>
    suspend fun getSources(category: String): Resource<List<Source>?>
    suspend fun getArticlesBySourceId(sourceId: String): Resource<List<Article>?>
    suspend fun searchForNews(searchQuery: String): Resource<List<Article>?>

}