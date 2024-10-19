package com.example.newsapp

import android.app.Application
import com.example.data.room.di.DatabaseModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

}