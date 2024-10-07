package com.example.data.repositories


import com.example.data.mappers.toArticleList
import com.example.data.mappers.toSourcesList
import com.example.data.repositories.data_sources.remote_data_source.NewsRemoteDataSource
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo

import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepo {

    override suspend fun getHeadLines(category: String): List<Article>? {
        return toArticleList(remoteDataSource.getHeadLines(category))
    }

    override suspend fun getSources(category: String): List<Source>? {
        return toSourcesList(remoteDataSource.getSources(category))
    }

    override suspend fun getArticlesBySourceId(sourceId: String):List<Article>?{
        return toArticleList(remoteDataSource.getArticlesBySourceId(sourceId))
    }


}