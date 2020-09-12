package com.virtualsoft.core.utils.validation

object PhoneValidation {

    fun isPhoneValid(phone: String): Boolean {
        val phoneFormatted = phone.replace("(", "")
            .replace("+", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")
        val number = phoneFormatted.toDoubleOrNull()
        return phoneFormatted.length == 11 && number != null
    }
}