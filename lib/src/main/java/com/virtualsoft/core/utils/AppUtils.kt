package com.virtualsoft.core.utils

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast


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

    fun Context.openPlayStoreSubscriptions() {
        val uri = Uri.parse("https://play.google.com/store/account/subscriptions")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun Context.openPlayStoreApp() {
        try {
            val uri = Uri.parse("market://details?id=$packageName")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        catch (e: Exception) {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    fun Context.openShareTextDialog(title: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(intent, title))
    }

    @ChecksSdkIntAtLeast(parameter = 0)
    fun checkVersionCompatibility(version: Int): Boolean {
        return Build.VERSION.SDK_INT >= version
    }
}