package com.embibe.iibnanded.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

/**
 * Created by mindstix on 16/06/18.
 */
class DashboardAdapter (var items: List<GetDashboardInfoResp>?, val context: Context) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var mListener : DashboardAdapter.OnItemClickListener? = null;
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items != null) {
            holder.tvTestName?.text = items!!.get(position).testName
            holder.tvNoOfQuestion?.text = items!!.get(position).noOfQuestions
            holder.tvDuration?.text = items!![position].testTotalTime + " Hrs"

        }
    }



    fun setData(items: List<GetDashboardInfoResp>) {
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
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvTestName = view.tv_test_name
        val tvNoOfQuestion = view.tv_no_of_questions
        val tvDuration = view.tv_duration
    }
}