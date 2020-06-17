package com.virtualsoft.core.utils

object TextUtils {

    fun emptyString(): String = ""

    fun String.nextAlphabeticString(): String {
        val strFrontCode = this.dropLast(1)
        val strEndCode = (this.last().toInt() + 1).toChar()
        return "$strFrontCode$strEndCode"
    }

    fun String.cropLastWhenTextIsBig(maxSize: Int, cropText: String): String {
        var resultText = this
        if (maxSize > 0 && maxSize > cropText.length && resultText.length > maxSize) {
            val dropSize = resultText.length - (maxSize - cropText.length)
            resultText = "${resultText.dropLast(dropSize)}$cropText"
        }
        return resultText
    }
}