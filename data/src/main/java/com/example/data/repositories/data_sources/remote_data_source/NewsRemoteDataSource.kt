package com.example.data.repositories.data_sources.remote_data_source

import com.example.data.api.WebServices
import com.example.data.api.models.news.ArticleDM
import javax.inject.Inject


class NewsRemoteDataSource @Inject constructor(
    private val webServices: WebServices
) {

    suspend fun getHeadLines(language: String): List<ArticleDM?>? {
        return webServices.getHeadLines(language).articles
    }

    suspend fun getArticlesBySourceId(sourceId: String): List<ArticleDM?>? {
        TODO("Not yet implemented")
    }

}