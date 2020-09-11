package com.virtualsoft.core.view.utils

import android.content.Context

object ResourceUtils {

    fun Context.getDimen(dimen: Int): Float {
        return resources.getDimension(dimen) / resources.displayMetrics.density
    }

    fun Context.getStringResource(stringRes: Int): String {
        return resources.getString(stringRes)
    }
}