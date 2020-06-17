package com.virtualsoft.core.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.virtualsoft.core.view.activities.BaseActivity

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    protected lateinit var fragmentContext: Context
    protected lateinit var fragmentView: View
    protected lateinit var baseActivity: BaseActivity
    protected lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            fragmentContext = requireContext()
            fragmentView = view
            baseActivity = activity as BaseActivity
            navController = baseActivity.navController
        }
        catch (e: Exception) {

        }
    }

    protected open fun initialize() {

    }

    protected open fun setupViews() {

    }

    protected open fun resetViews() {

    }

    protected fun setupBackPressed(action: (() -> Unit)? = null) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action?.invoke()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected fun setupFullScreen() {
        var bottomSheetBehavior: BottomSheetBehavior<View>? = null
        (fragmentView.parent as? View)?.let { viewParent ->
            bottomSheetBehavior = BottomSheetBehavior.from(viewParent)
        }
        val childLayoutParams = fragmentView.layoutParams
        val displayMetrics = DisplayMetrics()

        baseActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        childLayoutParams?.height = displayMetrics.heightPixels
        fragmentView.layoutParams = childLayoutParams
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }
}