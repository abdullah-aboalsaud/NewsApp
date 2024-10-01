package com.example.data.api

import com.example.data.api.models.headlines.HeadlineResponseDM
import com.example.data.api.models.news.NewsResponseDM
import com.example.data.api.models.sources.SourcesResponseDM
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("category") category: String
    ): SourcesResponseDM

    @GET("v2/everything")
    suspend fun getArticlesBySourceId(
        @Query("sources") sourceId: String
    ): NewsResponseDM

    @GET("v2/top-headlines")
    suspend fun getHeadLines(
        @Query("category") category: String
    ): HeadlineResponseDM

}
