package com.swarn.locusapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.adapter.ITEM_TYPE_COMMENT
import com.swarn.locusapp.adapter.ITEM_TYPE_PHOTO
import com.swarn.locusapp.adapter.ITEM_TYPE_SINGLE_CHOICE
import com.swarn.locusapp.data.CustomView
import com.swarn.locusapp.holder.BaseViewHolder
import com.swarn.locusapp.holder.CommentsViewHolder
import com.swarn.locusapp.holder.PhotoViewHolder
import com.swarn.locusapp.holder.SingleChoiceViewHolder

/**
 * @author Swarn Singh.
 */
class CustomViewRenderUtil {

    companion object {
        fun getCustomViewHolder(
            parent: ViewGroup,
            viewType: Int,
            adapter: CustomRecyclerViewAdapter
        ): BaseViewHolder<CustomView> {
            val itemView: View?

            return when (viewType) {
                ITEM_TYPE_PHOTO -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_photo_layout, parent, false)
                    PhotoViewHolder(itemView, adapter)
                }
                ITEM_TYPE_SINGLE_CHOICE -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_single_choice_layout, parent, false)
                    SingleChoiceViewHolder(itemView, adapter)
                }
                ITEM_TYPE_COMMENT -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_provide_comment_layout, parent, false)
                    CommentsViewHolder(itemView, adapter)
                }
                else -> throw IllegalArgumentException("Invalid view type")
            }

        }
    }

}