package com.example.newsapp

import android.app.Application
import com.example.newsapp.api.ApiManager
import com.example.newsapp.utils.AppNetworkHandler

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppNetworkHandler.init(this)
        ApiManager.initRetrofit(this)
    }
}