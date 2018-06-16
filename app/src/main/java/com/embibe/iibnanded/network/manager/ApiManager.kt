package com.embibe.iibnanded.network.manager

import android.content.Context
import android.util.Log

import com.embibe.iibnanded.network.api.getDashboardInfo.GetDashboardInfo
import com.embibe.iibnanded.network.client.RetrofitClient
import com.embibe.iibnanded.network.utils.BaseResponse
import com.embibe.iibnanded.network.utils.IResponsePublisher
import com.embibe.iibnanded.network.utils.ResponsePublisher

import retrofit2.Retrofit

/**
 * Created by IoT-Engg team on 11/06/18.
 */
class ApiManager private constructor(private val mContext: Context) : IApiManager {
    private var mBaseUrl: String? = null
    private val mResponsePublisher: ResponsePublisher<*>
    private val isDebugEnabled = true

    private val retrofitInstance: Retrofit
        get() = RetrofitClient.Builder().buildRequest()

    init {
        mResponsePublisher = ResponsePublisher<BaseResponse>()
    }

    override fun setBaseUrl(baseUrl: String) {
        mBaseUrl = baseUrl
    }

    override fun registerResponseObserver(responsePublisher: IResponsePublisher<*>) {
        mResponsePublisher.registerResponsePublisher(responsePublisher)
    }

    override fun unregisterResponseObserver(responsePublisher: IResponsePublisher<*>) {
        mResponsePublisher.unregisterResponsePublisher(responsePublisher)
    }

    override fun unregisterAllResponseObserver() {
        mResponsePublisher.unregisterAllResponsePublisher()
    }

    override fun getDashboardInfo(studentId: Int) {
        val getDashboardInfo = GetDashboardInfo(retrofitInstance, studentId, mResponsePublisher)
        getDashboardInfo.getDashboardInfoAPI()
    }

    override fun validateCredentials(userName: String, password: String) {

    }

    companion object {

        private var mIntance: ApiManager? = null

        val instance: ApiManager?
            get() {
                if (mIntance == null) {
                    Log.e("ApiManager", "InitializationException")
                }
                return mIntance
            }

        fun init(applicationContext: Context) {
            mIntance = ApiManager(applicationContext)
        }
    }
}
