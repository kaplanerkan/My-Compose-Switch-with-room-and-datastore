package com.example.mycomposeswitchwithdatastoreandroom.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Application-level initializations
    }
}