package com.example.data.repositories


import com.example.data.repositories.data_sources.remote_data_source.NewsRemoteDataSource
import com.example.domain.mappers.ArticleMapper
import com.example.domain.models.news.Article
import com.example.domain.repositories.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val articleMapper: ArticleMapper
) : NewsRepo {

    override suspend fun getHeadLines(language: String): List<Article>? {
        return articleMapper.toArticleList(remoteDataSource.getHeadLines(language))
    }

    override suspend fun getArticlesBySourceId(sourceId: String): List<Article>? {
        return articleMapper.toArticleList(remoteDataSource.getArticlesBySourceId(sourceId))
    }


}