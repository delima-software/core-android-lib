package com.virtualsoft.core.view.utils

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity

object ActivityUtils {

    fun AppCompatActivity.setStatusBarColor(@ColorInt color: Int, isDark: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.statusBarColor = color
        if (isDark)
            lightStatusBarForegroundColor()
        else
            darkStatusBarForegroundColor()
    }

    private fun AppCompatActivity.lightStatusBarForegroundColor() {
        val decorView = this.window.decorView
        var systemUiVisibilityFlags = decorView.systemUiVisibility
        systemUiVisibilityFlags = systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        decorView.systemUiVisibility = systemUiVisibilityFlags
    }

    private fun AppCompatActivity.darkStatusBarForegroundColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun AppCompatActivity.showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun AppCompatActivity.hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        var view = currentFocus
        if (view == null)
            view = View(this)
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}