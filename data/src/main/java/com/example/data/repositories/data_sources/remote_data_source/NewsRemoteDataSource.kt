package com.example.data.repositories.data_sources.remote_data_source

import com.example.data.api.WebServices
import com.example.data.api.models.headlines.SourceDM
import com.example.data.api.models.news.ArticleDM
import javax.inject.Inject


class NewsRemoteDataSource @Inject constructor(
    private val webServices: WebServices
) {

    suspend fun getHeadLines(category: String): List<ArticleDM?>? {
        return webServices.getHeadLines(category).articles
    }

    suspend fun getSources(category: String): List<SourceDM?>? {
        return webServices.getNewsSources(category).sources
    }

}