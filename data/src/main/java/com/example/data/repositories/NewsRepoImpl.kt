package com.example.data.repositories

import com.example.data.repositories.data_sources.remote_data_source.NewsRemoteDataSource
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo
import com.example.domain.utils.Resource

import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepo {

    override suspend fun getHeadLines(category: String): Resource<List<Article>?> {
        return remoteDataSource.getHeadLines(category)
    }

    override suspend fun getSources(category: String): Resource<List<Source>?> {
        return remoteDataSource.getSources(category)
    }

    override suspend fun getArticlesBySourceId(sourceId: String): Resource<List<Article>?>{
        return remoteDataSource.getArticlesBySourceId(sourceId)
    }

    override suspend fun searchForNews(searchQuery: String): Resource<List<Article>?> {
        return remoteDataSource.searchForNews(searchQuery)
    }


}