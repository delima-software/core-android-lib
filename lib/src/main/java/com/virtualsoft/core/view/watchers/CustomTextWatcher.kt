package com.virtualsoft.core.view.watchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class CustomTextWatcher(private val field: EditText, private val mask: String) : TextWatcher {

    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after;
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true

        field.removeTextChangedListener(this)
        s?.length?.let { editableLength ->
            if (editableLength < mask.length && editableLength > 0) {
                if (mask[editableLength] != '#') {
                    s.append(mask[editableLength])
                } else if (mask[editableLength - 1] != '#') {
                    s.insert(editableLength - 1, mask, editableLength - 1, editableLength)
                }
            }
        }
        field.addTextChangedListener(this)

        isRunning = false
    }
}