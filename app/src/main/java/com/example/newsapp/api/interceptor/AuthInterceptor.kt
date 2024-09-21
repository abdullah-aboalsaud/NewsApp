package com.example.newsapp.api.interceptor

import com.example.newsapp.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.header("Authorization", API_KEY)
        return chain.proceed(newBuilder.build())
    }

}