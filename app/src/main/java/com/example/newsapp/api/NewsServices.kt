package com.example.newsapp.api

import com.example.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {

    @GET("v2/top-headlines/sources")
    fun getNewsSources(): Call<SourcesResponse>


}