package com.virtualsoft.core.view.activities

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.AdProviders

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val navControllerId: Int

    val navController by lazy {
        findNavController(navControllerId)
    }

    protected open fun initialize() {

    }

    protected open fun setupViews() {

    }

    protected open fun resetViews() {

    }

    fun setStatusBarColor(@ColorInt color: Int, isDark: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.statusBarColor = color
        if (isDark)
            lightStatusBarForegroundColor()
        else
            darkStatusBarForegroundColor()
    }

    private fun lightStatusBarForegroundColor() {
        val decorView = this.window.decorView
        var systemUiVisibilityFlags = decorView.systemUiVisibility
        systemUiVisibilityFlags = systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        decorView.systemUiVisibility = systemUiVisibilityFlags
    }

    private fun darkStatusBarForegroundColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

}