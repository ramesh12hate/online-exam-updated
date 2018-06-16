package com.embibe.iibnanded.network.api.getDashboardInfo

import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import com.embibe.iibnanded.network.utils.AbstractCallback
import com.embibe.iibnanded.network.utils.BaseResponse
import com.embibe.iibnanded.network.utils.IResponsePublisher
import com.embibe.iibnanded.network.utils.RequestTypes
import com.vmware.iot.smart_interaction_provisioning_android.network.api.getEventSource.IGetDashboardInfo

import retrofit2.Callback
import retrofit2.Retrofit

/**
 * Created by IoT-Engg team on 11/06/18.
 */
class GetDashboardInfo(retrofit: Retrofit, private val mStudentId: Int, private val mResponsePublisher: IResponsePublisher<*>) : BaseResponse() {
    private val mIGetDashboardInfoAPI: IGetDashboardInfo.IGetDashboardInfoAPI

    init {
        mIGetDashboardInfoAPI = retrofit.create<IGetDashboardInfo.IGetDashboardInfoAPI>(IGetDashboardInfo.IGetDashboardInfoAPI::class.java!!)
    }

    fun getDashboardInfoAPI() {
        val call = mIGetDashboardInfoAPI.getDashboardInfoAPI(mStudentId)
        call.enqueue(AbstractCallback<GetDashboardInfoResp>(RequestTypes.GET_DASHBOARD_INFO, mResponsePublisher)as Callback<List<GetDashboardInfoResp>>)
    }
}
