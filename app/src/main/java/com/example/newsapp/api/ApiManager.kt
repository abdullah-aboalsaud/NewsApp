package com.example.newsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {
    private var retrofit: Retrofit? = null

    init {
        buildRetrofit()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(
            Logger { message: String ->
                Log.e("api->", message)
            }
        )
        loggingInterceptor.setLevel(Level.BODY)
        return loggingInterceptor
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build()
    }

    private fun buildRetrofit(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getServices(): NewsServices {
        return buildRetrofit().create(NewsServices::class.java)
    }
}