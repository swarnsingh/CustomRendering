package com.swarn.locusapp.holder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.data.CustomView
import com.swarn.locusapp.data.ViewType
import com.swarn.locusapp.ui.FullPictureActivity
import com.swarn.locusapp.util.DEFAULT_SIZE
import com.swarn.locusapp.util.PhotoUtil
import java.io.File

/**
 * @author Swarn Singh.
 */
class PhotoViewHolder(itemView: View, adapter: CustomRecyclerViewAdapter) :
    BaseViewHolder<CustomView>(itemView, adapter) {

    private var mAdapter: CustomRecyclerViewAdapter = adapter

    private var photoTitle = itemView.findViewById<TextView>(R.id.photo_title_txt_view)!!
    var photoImgView = itemView.findViewById<ImageView>(R.id.photo_img_view)!!
    var photoRemoveBadge = itemView.findViewById<ImageView>(R.id.photo_remove_badge)!!

    var sizeInPx = PhotoUtil.convertDpToPx(itemView.context, DEFAULT_SIZE).toInt()

    override fun onBind(holder: BaseViewHolder<CustomView>, position: Int) {
        val item = mAdapter.getData()!![position]
        (holder as PhotoViewHolder).photoTitle.text = item.title

        if (item.value != null) {
            Picasso.get()
                .load(File(item.value))
                .resize(sizeInPx, sizeInPx)
                .into(holder.photoImgView)

            holder.photoRemoveBadge.visibility = View.VISIBLE
        } else {
            holder.photoImgView.setImageBitmap(null)
            holder.photoImgView.setImageDrawable(itemView.context.getDrawable(R.drawable.edittext_bg))
            holder.photoRemoveBadge.visibility = View.GONE
        }

        holder.photoImgView.setOnClickListener {
            if (item.value == null) {
                (itemView.context as CustomRecyclerViewAdapter.CallbackInterface).onHandlePhotoSelection(
                    position, holder
                )
            } else {
                val intent = Intent(itemView.context, FullPictureActivity::class.java)
                intent.putExtra(ViewType.PHOTO.toString(), item.value)

                itemView.context.startActivity(intent)
            }
        }

        holder.photoRemoveBadge.setOnClickListener {
            val imageFile = File(item.value)

            if (imageFile.exists()) {
                imageFile.delete()
            }
            item.value = null
            holder.photoImgView.setImageBitmap(null)
            holder.photoImgView.setImageDrawable(itemView.context.getDrawable(R.drawable.edittext_bg))
            holder.photoRemoveBadge.visibility = View.GONE
            mAdapter.setData(item, position)
        }
    }
}