package com.virtualsoft.core.utils

object TypeUtils {

    fun Boolean?.isNullOrFalse(): Boolean {
        return this == null || this == false
    }
}