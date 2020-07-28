package com.virtualsoft.core.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected var context: Context? = null
    var setWaiting: ((Boolean) -> Unit)? = null

    open fun initViewModel(context: Context? = null) {
        this.context = context
    }
}