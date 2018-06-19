package com.embibe.iibnanded.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.model.QuestionListModel
import kotlinx.android.synthetic.main.item_list_question.view.*

/**
 * Created by mindstix on 18/06/18.
 */

class QuestionListAdapter  (var items: List<QuestionListModel>?, val context: Context) : RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items != null) {
            holder?.tvQuestionNumber.text = ""+items!!.get(position).questionNo
            if(items!!.get(position).questionNo % 3 == 0)
                holder?.tvQuestionNumber.background = context.resources.getDrawable(R.drawable.question_green_highlight)
            else if(items!!.get(position).questionNo % 4 == 0)
                holder?.tvQuestionNumber.background = context.resources.getDrawable(R.drawable.question_blue_highlight)
            else if(items!!.get(position).questionNo % 5 == 0)
                holder?.tvQuestionNumber.background = context.resources.getDrawable(R.drawable.question_gray_highlight)
            else
                holder?.tvQuestionNumber.background = context.resources.getDrawable(R.drawable.question_white_highlight)
        }
    }

    fun setData(items: List<QuestionListModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (items != null) {
            return items!!.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_question, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvQuestionNumber = view.tv_question_no
    }
}
