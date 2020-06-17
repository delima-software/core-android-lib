package com.virtualsoft.core.utils

import android.content.Context
import android.content.pm.ApplicationInfo

object AppUtils {

    const val DEVELOPMENT = "development"
    const val PRODUCTION = "production"
    const val STAGING = "staging"

    fun Context.isDebugging(): Boolean {
        return (this.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }

    fun Context.getEnvironment(): String {
        return if (this.isDebugging()) DEVELOPMENT else PRODUCTION
    }
}