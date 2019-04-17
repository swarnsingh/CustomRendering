package com.swarn.locusapp.holder

import android.app.Activity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.data.CustomView


/**
 * @author Swarn Singh.
 */
class SingleChoiceViewHolder(itemView: View, adapter: CustomRecyclerViewAdapter) :
    BaseViewHolder<CustomView>(itemView, adapter) {


    private var mAdapter: CustomRecyclerViewAdapter = adapter

    private var questionTitle = itemView.findViewById<TextView>(R.id.question_title_txt_view)!!

    private var answerRadioGroup = itemView.findViewById<RadioGroup>(R.id.ans_radio_group)!!
    private var option1 = itemView.findViewById<RadioButton>(R.id.option1)!!
    private var option2 = itemView.findViewById<RadioButton>(R.id.option2)!!
    private var option3 = itemView.findViewById<RadioButton>(R.id.option3)!!

    private lateinit var lastRadioButton: RadioButton


    override fun onBind(holder: BaseViewHolder<CustomView>, position: Int) {
        val item = mAdapter.getData()!![position]

        (holder as SingleChoiceViewHolder).questionTitle.text = item.title

        holder.option1.text = item.dataMap!!.options[0]
        holder.option2.text = item.dataMap!!.options[1]
        holder.option3.text = item.dataMap!!.options[2]

        if (item.value != null && position == adapterPosition) {
            when (item.value) {
                item.dataMap!!.options[0] -> {
                    holder.option1.isChecked = true
                }
                item.dataMap!!.options[1] -> {
                    holder.option2.isChecked = true
                }
                item.dataMap!!.options[2] -> {
                    holder.option3.isChecked = true
                }
            }
            val handler = (itemView.context as Activity).window.decorView.handler
            handler.post {
                mAdapter.notifyItemChanged(position)
            }
        } else {
            holder.option1.isChecked = false
            holder.option2.isChecked = false
            holder.option3.isChecked = false
        }

        holder.answerRadioGroup.setOnCheckedChangeListener { _, checkedId ->

            if (position == adapterPosition) {
                when (checkedId) {
                    R.id.option1 -> {
                        item.value = item.dataMap?.options?.get(0)
                        holder.option1.isChecked = true
                        mAdapter.setData(item, position)
                    }
                    R.id.option2 -> {
                        item.value = item.dataMap?.options?.get(1)
                        holder.option2.isChecked = true
                        mAdapter.setData(item, position)
                    }
                    R.id.option3 -> {
                        item.value = item.dataMap?.options?.get(2)
                        holder.option3.isChecked = true
                        mAdapter.setData(item, position)
                    }
                }
            }
        }
    }
}