package com.example.newsapp.api

import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String = API_KEY,
    ): Call<SourcesResponse>


}