package com.virtualsoft.core.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasePagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {

    protected var size = 0
    protected val tabTitles = mutableListOf<Int>()
    protected val tabIcons = mutableListOf<Int>()

    override fun getItemCount(): Int {
        return size
    }

    fun getPageTitle(position: Int, context: Context): CharSequence? {
        tabTitles.getOrNull(position)?.let { stringRes ->
            return context.resources.getString(stringRes)
        }
        return null
    }

    fun getPageIcon(position: Int, context: Context): Drawable? {
        tabIcons.getOrNull(position)?.let { iconRes ->
            return AppCompatResources.getDrawable(context, iconRes)
        }
        return null
    }
}