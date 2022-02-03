package com.virtualsoft.core.view.utils

import android.graphics.Paint
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.virtualsoft.core.view.utils.ViewUtils.gone

object ViewUtils {

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }

    fun ProgressBar.setWaiting(waiting: Boolean) {
        if (waiting)
            this.visible()
        else
            this.gone()
    }

    fun TextView.crossLine(activate: Boolean) {
        if (activate) {
            this.paintFlags.let {
                this.paintFlags = it or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        else {
            this.paintFlags.let {
                this.paintFlags = it and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    fun EditText.getContent(): String {
        return this.text.toString()
    }
}