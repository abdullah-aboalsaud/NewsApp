package com.example.data.repositories.data_sources.remote_data_source

import com.example.data.api.WebServices
import com.example.data.api.models.headlines.SourceDM
import com.example.data.api.models.news.ArticleDM
import com.example.data.executeApi
import com.example.data.mappers.ArticleMapper
import com.example.data.mappers.ArticleMapper.Companion.toArticleList
import com.example.data.mappers.toSourcesList
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.utils.Resource
import javax.inject.Inject


class NewsRemoteDataSource @Inject constructor(
    private val webServices: WebServices
) {

    suspend fun getHeadLines(category: String): Resource<List<Article>?> {
        val response = webServices.getHeadLines(category).articles
        return executeApi {
            // convert from List<ArticleDM> to List<Article>
            toArticleList(response)
        }

    }

    suspend fun getSources(category: String): Resource<List<Source>?> {
        val response = webServices.getNewsSources(category).sources
        return executeApi {
            toSourcesList(response)
        }
    }

    suspend fun getArticlesBySourceId(sourceId: String): Resource<List<Article>?> {
        val response = webServices.getArticlesBySourceId(sourceId).articles
        return executeApi {
            toArticleList(response)
        }
    }

    suspend fun searchForNews(searchQuery: String): Resource<List<Article>?> {
        val response = webServices.searchForNews(searchQuery).articles
        return executeApi {
            toArticleList(response)
        }
    }

}