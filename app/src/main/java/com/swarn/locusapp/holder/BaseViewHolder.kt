package com.swarn.locusapp.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Swarn Singh.
 */
abstract class BaseViewHolder<T>(itemView: View, adapter: RecyclerView.Adapter<*>) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(holder: BaseViewHolder<T>, position: Int)
}