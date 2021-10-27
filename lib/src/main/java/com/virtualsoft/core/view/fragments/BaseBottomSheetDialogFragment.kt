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

    protected open fun initialize() {

    }

    protected open fun setupViews() {

    }

    protected open fun resetViews() {

    }
}