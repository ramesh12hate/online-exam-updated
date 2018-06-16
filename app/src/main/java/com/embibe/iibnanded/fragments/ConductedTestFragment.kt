package com.embibe.iibnanded.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.embibe.iibnanded.R
import com.embibe.iibnanded.adapters.DashboardAdapter
import com.embibe.iibnanded.network.manager.ApiManager
import com.embibe.iibnanded.network.manager.IApiManager
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import com.embibe.iibnanded.network.utils.BaseResponse
import com.embibe.iibnanded.network.utils.IResponsePublisher
import kotlinx.android.synthetic.main.fragment_conducted_test.*
import retrofit2.Call
import retrofit2.Response

class ConductedTestFragment : Fragment() {
    private lateinit var mMainView: View
    private var apiManager: IApiManager? = null
    val animals: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ApiManager.init(this@ConductedTestFragment.context!!)
        apiManager = ApiManager.instance
        apiManager?.registerResponseObserver(responsePublisher)
        mMainView = inflater.inflate(R.layout.fragment_conducted_test, container, false)
        return mMainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list.layoutManager = LinearLayoutManager(this@ConductedTestFragment.context)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@ConductedTestFragment.context, "in resume", Toast.LENGTH_SHORT).show();
        apiManager?.getDashboardInfo(20)
    }


    private val responsePublisher = object: IResponsePublisher<BaseResponse> {
        override fun onSuccess(requestType: Int, call: Call<*>, responseBean: BaseResponse) {

        }

        override fun onSuccess(requestType: Int, call: Call<*>, response: Response<*>) {
            if (response != null) {
                handleResult(response.body() as ArrayList<GetDashboardInfoResp>)
            }
        }

        override fun onUnauthorised(requestType: Int, call: Call<*>, responseBean: BaseResponse) {
            Toast.makeText(this@ConductedTestFragment.activity, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        override fun onError(requestType: Int, call: Call<*>, error: Throwable) {
            if (error != null) {
                Toast.makeText(this@ConductedTestFragment.activity, "Something went wrong2", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private  fun handleResult(list : ArrayList<GetDashboardInfoResp>){
        rv_list.adapter = DashboardAdapter(list, this@ConductedTestFragment.context!!)
    }
}
