package com.virtualsoft.core.view.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

object FragmentUtils {

    fun Fragment.hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun BottomSheetDialogFragment.setupFullScreen() {
        var bottomSheetBehavior: BottomSheetBehavior<View>? = null
        (view?.parent as? View)?.let { viewParent ->
            bottomSheetBehavior = BottomSheetBehavior.from(viewParent)
        }
        val childLayoutParams = view?.layoutParams
        val displayMetrics = DisplayMetrics()

        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        childLayoutParams?.height = displayMetrics.heightPixels
        view?.layoutParams = childLayoutParams
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }
}