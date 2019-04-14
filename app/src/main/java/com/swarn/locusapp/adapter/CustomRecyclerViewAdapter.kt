package com.swarn.locusapp.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.data.CustomView
import com.swarn.locusapp.data.ViewType
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

class CustomRecyclerViewAdapter(context: Context, customViewData: MutableList<CustomView>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mCustomViewData: MutableList<CustomView> = customViewData

    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): RecyclerView.ViewHolder {
        return CustomViewRenderUtil.getCustomViewHolder(parent, itemViewType)!!
    }

    override fun getItemCount(): Int {
        return mCustomViewData.size
    }

    fun getData(): List<CustomView>? {
        return mCustomViewData
    }

    fun setData(customView: CustomView, position: Int) {
        mCustomViewData[position] = customView
    }

    override fun getItemViewType(position: Int): Int {

        val viewType = mCustomViewData[position].type

        when (viewType) {
            ViewType.PHOTO.toString() -> {
                return ITEM_TYPE_PHOTO
            }
            ViewType.SINGLE_CHOICE.toString() -> {
                return ITEM_TYPE_SINGLE_CHOICE
            }
            ViewType.COMMENT.toString() -> {
                return ITEM_TYPE_COMMENT
            }
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemType = getItemViewType(position)

        when (itemType) {
            ITEM_TYPE_PHOTO -> {
                (holder as PhotoViewHolder).onBind(position, holder, this)
            }
            ITEM_TYPE_SINGLE_CHOICE -> {
                (holder as SingleChoiceViewHolder).onBind(position, holder, this)
            }
            ITEM_TYPE_COMMENT -> {
                (holder as CommentsViewHolder).onBind(position, holder, this)
            }
        }
    }

    interface CallbackInterface {
        fun onHandlePhotoSelection(position: Int, holder: RecyclerView.ViewHolder)
    }
}