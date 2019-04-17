package com.swarn.locusapp.holder

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.data.CustomView

/**
 * @author Swarn Singh.
 */
class CommentsViewHolder(itemView: View, adapter: CustomRecyclerViewAdapter) :
    BaseViewHolder<CustomView>(itemView, adapter) {


    private var mAdapter: CustomRecyclerViewAdapter = adapter

    private var commentTitle = itemView.findViewById<TextView>(R.id.comment_title_txt_view)!!
    var commentSwitch = itemView.findViewById<Switch>(R.id.comment_switch)!!
    private var commentEditText = itemView.findViewById<EditText>(R.id.comment_edit_txt)!!

    override fun onBind(holder: BaseViewHolder<CustomView>, position: Int) {
        val item = mAdapter.getData()!![position]
        (holder as CommentsViewHolder).commentTitle.text = item.title

        if (position == adapterPosition) {
            if (item.value == "") {
                holder.commentEditText.visibility = View.GONE
                holder.commentSwitch.isChecked = false
            } else {
                holder.commentSwitch.isChecked = true
                holder.commentEditText.visibility = View.VISIBLE
                holder.commentEditText.setText(item.value)
            }

        }

        holder.commentSwitch.setOnClickListener {

            if (!holder.commentSwitch.isChecked) {
                item.value = ""
                mAdapter.setData(item, position)
                holder.commentEditText.visibility = View.GONE
            } else {
                item.value = null
                mAdapter.setData(item, position)
                holder.commentEditText.visibility = View.VISIBLE
            }
            val handler = (itemView.context as Activity).window.decorView.handler
            handler.post {
                mAdapter.notifyItemChanged(position)
            }
        }

        holder.commentEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (position == adapterPosition) {
                    if (count > 0) {
                        item.value = s.toString()

                    } else {
                        holder.commentSwitch.isChecked = !(holder.commentSwitch.isChecked && item.value == "")
                    }
                    mAdapter.setData(item, position)
                }
            }
        })
    }
}