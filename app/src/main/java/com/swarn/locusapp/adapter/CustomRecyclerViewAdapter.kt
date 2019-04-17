package com.swarn.locusapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.data.CustomView
import com.swarn.locusapp.data.ViewType
import com.swarn.locusapp.holder.BaseViewHolder
import com.swarn.locusapp.holder.CommentsViewHolder
import com.swarn.locusapp.holder.PhotoViewHolder
import com.swarn.locusapp.holder.SingleChoiceViewHolder
import com.swarn.locusapp.util.CustomViewRenderUtil

/**
 * @author Swarn Singh.
 */

const val ITEM_TYPE_PHOTO = 0
const val ITEM_TYPE_SINGLE_CHOICE = 1
const val ITEM_TYPE_COMMENT = 2

class CustomRecyclerViewAdapter() :
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    private var mCustomViewData: MutableList<CustomView>

    init {
        mCustomViewData = ArrayList()
    }

    fun setData(customViewData: MutableList<CustomView>) {
        mCustomViewData = customViewData
    }

    fun getData(): List<CustomView>? {
        return mCustomViewData
    }

    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): BaseViewHolder<*> {
        return CustomViewRenderUtil.getCustomViewHolder(parent, itemViewType, this)
    }

    override fun getItemCount(): Int {
        return mCustomViewData.size
    }

    fun setData(customView: CustomView, position: Int) {
        mCustomViewData[position] = customView
    }

    override fun getItemViewType(position: Int): Int {

        val viewType = mCustomViewData[position].type

        return when (viewType) {
            ViewType.PHOTO.toString() -> {
                ITEM_TYPE_PHOTO
            }
            ViewType.SINGLE_CHOICE.toString() -> {
                ITEM_TYPE_SINGLE_CHOICE
            }
            ViewType.COMMENT.toString() -> {
                ITEM_TYPE_COMMENT
            }
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PhotoViewHolder -> holder.onBind(holder, position)
            is SingleChoiceViewHolder -> holder.onBind(holder, position)
            is CommentsViewHolder -> holder.onBind(holder, position)

            else -> throw IllegalArgumentException()
        }
    }

    interface CallbackInterface {
        fun onHandlePhotoSelection(position: Int, holder: RecyclerView.ViewHolder)
    }
}