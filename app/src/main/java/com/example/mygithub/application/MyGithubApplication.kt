package com.example.mygithub.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyGithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}