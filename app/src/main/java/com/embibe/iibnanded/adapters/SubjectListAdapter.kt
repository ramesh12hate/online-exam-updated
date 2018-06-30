package com.embibe.iibnanded.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.model.QuestionListModel
import com.embibe.iibnanded.model.SubjectModel
import kotlinx.android.synthetic.main.item_list_question.view.*
import kotlinx.android.synthetic.main.item_list_subject.view.*

/**
 * Created by mindstix on 18/06/18.
 */

class SubjectListAdapter(var items: List<SubjectModel>?, val context: Context) : RecyclerView.Adapter<SubjectListAdapter.ViewHolder>() {
    private var clickListener: ItemClickListener? = null

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items!![position])
    }

    fun setData(items: List<SubjectModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_subject, parent, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            if (clickListener != null) clickListener!!.onClick(v, adapterPosition)
        }

        fun bindItems(mSubjectModel: SubjectModel) {
            itemView.tv_subject_no.text = mSubjectModel.subjectName
            itemView.setOnClickListener(this)
        }
    }

}