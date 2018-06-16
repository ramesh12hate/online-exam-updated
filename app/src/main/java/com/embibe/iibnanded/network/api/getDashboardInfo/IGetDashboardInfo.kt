package com.vmware.iot.smart_interaction_provisioning_android.network.api.getEventSource

import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp;

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by IoT-Engg team on 11/06/18.
 */
interface IGetDashboardInfo {

    interface IGetDashboardInfoAPI {
        @GET("studentDashboard.php")
        fun getDashboardInfoAPI(@Query("studentId") studentId : Int): Call<List<GetDashboardInfoResp>>
    }

    fun getDashboardInfoAPI()
}
