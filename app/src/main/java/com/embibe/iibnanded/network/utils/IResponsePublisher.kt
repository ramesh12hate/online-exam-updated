package com.embibe.iibnanded.network.utils

import retrofit2.Call
import retrofit2.Response

/**
 * Created by IoT-Engg team on 11/06/18.
 */
interface IResponsePublisher<T : BaseResponse> {

    /**
     * Called when network call is successful
     *
     * @param responseBean - network response parsed in corresponding class type.
     */
    fun onSuccess(@RequestTypes.Interface requestType: Int, call: Call<*>, responseBean: T)

    fun onSuccess(@RequestTypes.Interface requestType: Int, call: Call<*>, response: Response<*>)

    /**
     * Called when user is unauthorized to access the request resource.
     * @param call         - object of call
     * @param responseBean - response bean
     */
    fun onUnauthorised(@RequestTypes.Interface requestType: Int, call: Call<*>, responseBean: T)

    /**
     * Called when error occurred in the network call
     * @param call        - object of call
     * @param error       - error occurred in network call
     */
    fun onError(@RequestTypes.Interface requestType: Int, call: Call<*>, error: Throwable)
}
