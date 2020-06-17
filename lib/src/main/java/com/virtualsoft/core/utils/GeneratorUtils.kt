package com.virtualsoft.core.utils

import java.util.*

object GeneratorUtils {

    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }
}