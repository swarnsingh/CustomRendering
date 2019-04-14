package com.swarn.locusapp.holder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter

/**
 * @author Swarn Singh.
 */
class CommentsViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var commentTitle = itemView.findViewById<TextView>(R.id.comment_title_txt_view)!!

    private var commentSwitch = itemView.findViewById<Switch>(R.id.comment_switch)!!
    private var commentEditText = itemView.findViewById<EditText>(R.id.comment_edit_txt)!!

    fun onBind(position: Int, holder: CommentsViewHolder, adapter: CustomRecyclerViewAdapter) {
        val customView = adapter.getData()!![position]

        holder.commentTitle.text = customView.title
        holder.commentEditText.setText(customView.value)

        commentSwitch.setOnClickListener {
            if (commentEditText.isVisible) {
                commentEditText.visibility = View.GONE
                commentEditText.text = null
                customView.value = null
                adapter.setData(customView, position)
            } else {
                commentEditText.visibility = View.VISIBLE
            }
        }

        commentEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                customView.value = s.toString()
                adapter.setData(customView, position)
            }
        })
    }
}