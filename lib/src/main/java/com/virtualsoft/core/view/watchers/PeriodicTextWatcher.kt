package com.virtualsoft.core.view.watchers

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class PeriodicTextWatcher(val listener: () -> Unit): TextWatcher {

    private var timer: Timer? = null
    private val DELAY: Long = 1000

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        timer?.cancel()
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let { editable ->
            if (editable.length > 5) {
                timer = Timer()
                timer?.schedule(object: TimerTask() {
                    override fun run() {
                        GlobalScope.launch(Dispatchers.Main) {
                            listener()
                        }
                    }
                }, DELAY)
            }
        }
    }
}