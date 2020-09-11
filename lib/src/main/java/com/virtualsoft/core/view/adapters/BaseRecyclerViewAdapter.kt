package com.virtualsoft.core.view.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.virtualsoft.core.designpatterns.builder.IBuild
import com.virtualsoft.core.view.viewholders.BaseViewHolder

abstract class BaseRecyclerViewAdapter <T : BaseViewHolder>(open val context: Context) :
    RecyclerView.Adapter<T>(), IBuild