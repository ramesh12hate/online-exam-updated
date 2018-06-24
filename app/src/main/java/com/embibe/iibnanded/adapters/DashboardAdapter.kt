package com.embibe.iibnanded.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.activities.DashboardActivity
import com.embibe.iibnanded.activities.QuestionActivity
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by mindstix on 16/06/18.
 */
class DashboardAdapter(private var items: List<GetDashboardInfoResp>?, val context: Context) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items!![position])
    }


    fun setData(items: List<GetDashboardInfoResp>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: GetDashboardInfoResp) {
            itemView.tv_test_name.text = item.testName
            itemView.tv_no_of_questions.text = item.noOfQuestions
            itemView.tv_duration.text = item.noOfQuestions

            if (item.testStatus.equals("Upcomming")) {
                itemView.setOnClickListener {
                    context.startActivity<QuestionActivity>()
                    (context as DashboardActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }
}