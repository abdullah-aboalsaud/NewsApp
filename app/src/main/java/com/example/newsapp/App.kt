package com.example.newsapp

import android.app.Application
import com.example.newsapp.utils.AppNetworkHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppNetworkHandler.init(this)

    }
}