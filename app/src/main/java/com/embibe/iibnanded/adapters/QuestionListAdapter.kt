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

class QuestionListAdapter(var items: List<QuestionListModel>?, val context: Context) : RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {
    private var clickListener: ItemClickListener? = null

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items!![position])
    }

    fun setData(items: List<QuestionListModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_question, parent, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            if (clickListener != null) clickListener!!.onClick(v, adapterPosition)
        }

        fun bindItems(questionModel: QuestionListModel) {
            itemView.tv_question_no.text = questionModel.questionNo.toString()
            if (questionModel.questionStatus == 2)
                itemView.tv_question_no.background = itemView.context.resources.getDrawable(R.drawable.question_green_highlight)
            else
                itemView.context.resources.getDrawable(R.drawable.question_blue_highlight)
            itemView.setOnClickListener(this)
//            when {
//                questionModel.questionNo % 3 == 0 -> itemView.tv_question_no.background = itemView.context.resources.getDrawable(R.drawable.question_green_highlight)
//                questionModel.questionNo % 4 == 0 -> itemView.tv_question_no.background = itemView.context.resources.getDrawable(R.drawable.question_blue_highlight)
//                questionModel.questionNo % 5 == 0 -> itemView.tv_question_no.background = itemView.context.resources.getDrawable(R.drawable.question_gray_highlight)
//                else -> itemView.tv_question_no.background = itemView.context.resources.getDrawable(R.drawable.question_white_highlight)
//            }
        }
    }

}
