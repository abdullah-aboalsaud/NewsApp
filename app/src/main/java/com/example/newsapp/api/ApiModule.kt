package com.example.newsapp.api

import android.content.Context
import android.util.Log
import com.example.newsapp.api.interceptor.AuthInterceptor
import com.example.newsapp.api.interceptor.CacheInterceptor
import com.example.newsapp.api.interceptor.OfflineCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(
            Logger { message: String ->
                Log.e("api->", message)
            }
        )
        loggingInterceptor.setLevel(Level.BODY)
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        cache: Cache,
        cacheInterceptor: CacheInterceptor,
        offlineCacheInterceptor: OfflineCacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(offlineCacheInterceptor)
            .build()
    }

    private fun provideCache(context: Context): Cache {
        return Cache(
            File(context.cacheDir, "news-cache"),
            10L * 1024L * 1024L
        ) // 10 MiB
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsServices(retrofit: Retrofit): NewsServices {
        return retrofit.create(NewsServices::class.java)
    }

}