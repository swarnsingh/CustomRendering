package com.swarn.locusapp.holder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.data.ViewType
import com.swarn.locusapp.ui.FullPictureActivity
import com.swarn.locusapp.util.PhotoUtil
import java.io.File

/**
 * @author Swarn Singh.
 */
class PhotoViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var mContext = itemView.context

    private var photoTitle = itemView.findViewById<TextView>(R.id.photo_title_txt_view)!!
    var photoImgView = itemView.findViewById<ImageView>(R.id.photo_img_view)!!
    var photoRemoveBadge = itemView.findViewById<ImageView>(R.id.photo_remove_badge)!!

    fun onBind(position: Int, holder: PhotoViewHolder, adapter: CustomRecyclerViewAdapter) {
        val customView = adapter.getData()!![position]
        holder.photoTitle.text = customView.title

        if (customView.value != null) {
            PhotoUtil.setPic(holder.photoImgView, customView.value)
            holder.photoRemoveBadge.visibility = View.VISIBLE
        } else {
            holder.photoRemoveBadge.visibility = View.GONE
        }

        photoImgView.setOnClickListener {
            if (customView.value == null) {
                (mContext as CustomRecyclerViewAdapter.CallbackInterface).onHandlePhotoSelection(position, holder)
            } else {
                val intent = Intent(mContext, FullPictureActivity::class.java)
                intent.putExtra(ViewType.PHOTO.toString(), customView.value)

                mContext.startActivity(intent)
            }
        }

        photoRemoveBadge.setOnClickListener {
            val imageFile = File(customView.value)

            if (imageFile.exists()) {
                imageFile.delete()
            }
            customView.value = null
            photoImgView.setImageBitmap(null)
            photoImgView.setImageDrawable(mContext.getDrawable(R.drawable.edittext_bg))
            photoRemoveBadge.visibility = View.GONE

            adapter.setData(customView, position)
            adapter.notifyDataSetChanged()
        }
    }
}