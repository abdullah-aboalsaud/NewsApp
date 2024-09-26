package com.example.data.api

import com.example.data.api.model.news.NewsResponse
import com.example.data.api.model.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("sources") sourceId: String
    ): NewsResponse


}