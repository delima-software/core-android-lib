package com.virtualsoft.core.view.watchers

import android.text.Editable
import android.text.TextWatcher
import com.virtualsoft.core.designpatterns.builder.IBuild
import com.virtualsoft.core.designpatterns.builder.IBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class PeriodicTextWatcher(val listener: () -> Unit): TextWatcher, IBuild {

    private var timer: Timer? = null
    private var DELAY: Long = 1000
    private var LENGTH: Int = 5

    class Builder(val listener: () -> Unit): IBuilder<PeriodicTextWatcher> {
        override val building = PeriodicTextWatcher(listener)

        fun setDelay(timeInMillis: Long): Builder {
            building.DELAY = timeInMillis
            return this
        }

        fun setEditableLength(length: Int): Builder {
            building.LENGTH = length
            return this
        }
    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        timer?.cancel()
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let { editable ->
            if (editable.length > LENGTH) {
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