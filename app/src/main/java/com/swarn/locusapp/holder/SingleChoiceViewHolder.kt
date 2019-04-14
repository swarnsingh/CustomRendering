package com.swarn.locusapp.holder

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter

/**
 * @author Swarn Singh.
 */
class SingleChoiceViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var questionTitle = itemView.findViewById<TextView>(R.id.question_title_txt_view)!!

    private var answerRadioGroup = itemView.findViewById<RadioGroup>(R.id.ans_radio_group)!!
    private var option1 = itemView.findViewById<RadioButton>(R.id.option1)!!
    private var option2 = itemView.findViewById<RadioButton>(R.id.option2)!!
    private var option3 = itemView.findViewById<RadioButton>(R.id.option3)!!

    fun onBind(position: Int, holder: SingleChoiceViewHolder, adapter: CustomRecyclerViewAdapter) {
        val customView = adapter.getData()!![position]

        holder.questionTitle.text = customView.title

        holder.option1.text = customView.dataMap!!.options[0]
        holder.option2.text = customView.dataMap!!.options[1]
        holder.option3.text = customView.dataMap!!.options[2]

        if (customView.value != null) {
            when (customView.value) {
                customView.dataMap!!.options[0] -> {
                    holder.option1.isChecked = true
                }
                customView.dataMap!!.options[1] -> {
                    holder.option2.isChecked = true
                }
                customView.dataMap!!.options[2] -> {
                    holder.option3.isChecked = true
                }
            }
        }

        answerRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option1 -> {
                    customView.value = adapter.getData()!![position].dataMap?.options?.get(0)
                }
                R.id.option2 -> {
                    customView.value = adapter.getData()!![position].dataMap?.options?.get(1)
                }
                R.id.option3 -> {
                    customView.value = adapter.getData()!![position].dataMap?.options?.get(2)
                }
            }
            adapter.setData(customView, position)
        }
    }
}