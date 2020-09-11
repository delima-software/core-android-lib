package com.virtualsoft.core.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasePagerAdapter(val context: Context, val fragment: Fragment) : FragmentStateAdapter(fragment) {

    protected var size = 0
    protected val tabTitles = mutableListOf<Int>()

    override fun getItemCount(): Int {
        return size
    }

    fun getPageTitle(position: Int): CharSequence? {
        tabTitles.getOrNull(position)?.let { stringRes ->
            return context.resources.getString(stringRes)
        }
        return null
    }
}