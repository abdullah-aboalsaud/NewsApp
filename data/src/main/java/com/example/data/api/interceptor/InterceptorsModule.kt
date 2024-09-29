package com.example.data.api.interceptor

import android.content.Context
import com.example.data.api.AppNetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(networkHandler: AppNetworkHandler): OfflineCacheInterceptor {
        return OfflineCacheInterceptor(networkHandler)
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, "news-cache"),
            10L * 1024L * 1024L
        ) // 10 MiB
    }


}