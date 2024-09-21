package com.example.newsapp.api

import android.content.Context
import android.util.Log
import com.example.newsapp.api.interceptor.AuthInterceptor
import com.example.newsapp.api.interceptor.CacheInterceptor
import com.example.newsapp.api.interceptor.OfflineCacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object ApiManager {
    private var retrofit: Retrofit? = null


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(
            Logger { message: String ->
                Log.e("api->", message)
            }
        )
        loggingInterceptor.setLevel(Level.BODY)
        return loggingInterceptor
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(AuthInterceptor())
            .cache(provideCache(context))
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(OfflineCacheInterceptor())
            .build()
    }

    private fun provideCache(context: Context): Cache {
        return Cache(
            File(context.cacheDir, "news-cache"),
            10L * 1024L * 1024L
        ) // 10 MiB
    }

    fun initRetrofit(context: Context): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(provideOkHttpClient(context))
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getServices(): NewsServices {
        return retrofit!!.create(NewsServices::class.java)
    }

}