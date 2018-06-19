package com.embibe.iibnanded.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R
import com.embibe.iibnanded.activities.DashboardActivity
import com.embibe.iibnanded.adapters.DashboardAdapter
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import kotlinx.android.synthetic.main.fragment_conducted_test.*

class ConductedTestFragment : Fragment(), DashboardActivity.OnDashboardDataReceivedListener{
    lateinit var adapter : DashboardAdapter;
    override fun onDataReceived(list: ArrayList<GetDashboardInfoResp>) {
//        adapter.setData(list)
//        adapter.notifyDataSetChanged()
        adapter = DashboardAdapter(list, this@ConductedTestFragment.context!!)
        rv_list.adapter = adapter
    }

    private lateinit var mMainView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mMainView = inflater.inflate(R.layout.fragment_conducted_test, container, false)
        val mActivity = activity as DashboardActivity?
        mActivity!!.setConductedDashboardDataListener(this)
        return mMainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list.layoutManager = LinearLayoutManager(this@ConductedTestFragment.context)
    }
}
