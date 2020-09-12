package com.virtualsoft.core.utils.validation

import android.util.Patterns

object EmailValidation {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}