package com.virtualsoft.core.utils.validation

object PasswordValidation {

    enum class PasswordStrength {
        WEAK,
        MEDIUM,
        STRONG,
        VERY_STRONG
    }

    private val MIN_LENGTH = 8
    private val MAX_LENGTH = 15

    fun calculate(password: String): PasswordStrength? {
        var score = 0
        // boolean indicating if password has an upper case
        var upper = false
        // boolean indicating if password has a lower case
        var lower = false
        // boolean indicating if password has at least one digit
        var digit = false
        // boolean indicating if password has a leat one special char
        var specialChar = false
        for (element in password) {
            if (!specialChar && !Character.isLetterOrDigit(element)) {
                score++
                specialChar = true
            } else {
                if (!digit && Character.isDigit(element)) {
                    score++
                    digit = true
                } else {
                    if (!upper || !lower) {
                        if (Character.isUpperCase(element)) {
                            upper = true
                        } else {
                            lower = true
                        }
                        if (upper && lower) {
                            score++
                        }
                    }
                }
            }
        }
        val length = password.length
        if (length > MAX_LENGTH) {
            score++
        } else if (length < MIN_LENGTH) {
            score = 0
        }
        when (score) {
            0 -> return PasswordStrength.WEAK
            1 -> return PasswordStrength.MEDIUM
            2 -> return PasswordStrength.STRONG
            3 -> return PasswordStrength.VERY_STRONG
            else -> {
            }
        }
        return PasswordStrength.VERY_STRONG
    }

    fun isPasswordValid(password: String, strength: PasswordStrength): Boolean {
        return calculate(password) == strength
    }
}