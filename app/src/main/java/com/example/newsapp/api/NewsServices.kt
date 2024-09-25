package com.example.newsapp.api

import com.example.newsapp.api.model.news.NewsResponse
import com.example.newsapp.api.model.sources.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("sources") sourceId: String
    ): Call<NewsResponse>


}