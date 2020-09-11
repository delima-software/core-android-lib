package com.virtualsoft.core.view.watchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class MoneyTextWatcher(private val editText: EditText, private var locale: Locale? = null) :
    TextWatcher {

    private var editTextWeakReference: WeakReference<EditText> = WeakReference(editText)
    private var currentText: String? = null

    init {
        if (locale == null)
            locale = Locale.getDefault()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable?) {
        val editText = editTextWeakReference.get()?: return
        locale?.let {
            editText.removeTextChangedListener(this)
            val parsed = parseToBigDecimal(editable.toString(), it)
            val formatted = NumberFormat.getCurrencyInstance(it).format(parsed)
            currentText = formatted
            editText.setText(formatted)
            editText.setSelection(formatted.length)
            editText.addTextChangedListener(this)
        }
    }

    private fun parseToBigDecimal(value: String, locale: Locale): BigDecimal? {
        val replaceable = String.format("[%s,.\\s]",
            NumberFormat.getCurrencyInstance(locale).currency?.symbol)
        val cleanString = value.replace(replaceable.toRegex(), "")
        return BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR)
            .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    }

    fun getCurrentValue(): BigDecimal? {
        currentText?.let { text ->
            locale?.let {
                return parseToBigDecimal(text, it)
            }
        }
        return null
    }
}