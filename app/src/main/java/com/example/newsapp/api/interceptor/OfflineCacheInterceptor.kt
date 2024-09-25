package com.example.newsapp.api.interceptor

import com.example.newsapp.utils.AppNetworkHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class OfflineCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val maxStale = 60 * 60 * 24 * 10 // offline cache available for 30 days

        if (!AppNetworkHandler.isInternetAvailable()) {
            builder.removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached$maxStale")
        }

        return chain.proceed(builder.build());


    }

}