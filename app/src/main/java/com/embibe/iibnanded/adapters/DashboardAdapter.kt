package com.embibe.iibnanded.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by mindstix on 16/06/18.
 */
class DashboardAdapter (val items : List<GetDashboardInfoResp>, val context: Context) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvType?.text = items.get(position).testName
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val tvType = view.tv_type
    }
}