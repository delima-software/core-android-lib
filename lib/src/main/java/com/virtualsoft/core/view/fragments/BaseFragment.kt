package com.virtualsoft.core.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.virtualsoft.core.view.activities.BaseActivity

abstract class BaseFragment : Fragment() {

    protected lateinit var fragmentContext: Context
    protected lateinit var baseActivity: BaseActivity
    protected lateinit var fragmentView: View
    protected lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            fragmentContext = requireContext()
            baseActivity = activity as BaseActivity
            fragmentView = view
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
}