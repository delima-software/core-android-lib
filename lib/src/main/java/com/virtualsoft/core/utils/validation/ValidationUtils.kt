package com.virtualsoft.core.utils.validation

object ValidationUtils {

    fun validateValues(validations: List<() -> Boolean>): Boolean {
        var isAllValid = true
        for (isValid in validations) {
            if (!isValid()) {
                isAllValid = false
                break
            }
        }
        return isAllValid
    }
}