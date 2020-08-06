package com.virtualsoft.core.app

import android.app.Application
import android.util.Log
import com.virtualsoft.core.utils.NetworkUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkUtils.initialize(this)
    }
}