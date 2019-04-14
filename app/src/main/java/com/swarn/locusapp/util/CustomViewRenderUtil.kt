package com.swarn.locusapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.ITEM_TYPE_COMMENT
import com.swarn.locusapp.adapter.ITEM_TYPE_PHOTO
import com.swarn.locusapp.adapter.ITEM_TYPE_SINGLE_CHOICE
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
            viewType: Int
        ): RecyclerView.ViewHolder? {
            val itemView: View?

            var viewHolder: RecyclerView.ViewHolder? = null

            when (viewType) {
                ITEM_TYPE_PHOTO -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_photo_layout, parent, false)
                    viewHolder = PhotoViewHolder(itemView)
                }
                ITEM_TYPE_SINGLE_CHOICE -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_single_choice_layout, parent, false)
                    viewHolder = SingleChoiceViewHolder(itemView)
                }
                ITEM_TYPE_COMMENT -> {
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_provide_comment_layout, parent, false)
                    viewHolder = CommentsViewHolder(itemView)
                }
            }
            return viewHolder
        }
    }

}