package com.example.myapplication.application

import android.app.Application;
import android.util.Log

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.i("MainApplication", "onCreate()")
    }
}
