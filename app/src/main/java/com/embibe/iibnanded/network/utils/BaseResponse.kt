package com.embibe.iibnanded.network.utils

import retrofit2.Response

/**
 * Created by IoT-Engg team on 11/06/18.
 */
open class BaseResponse {
    var baseMessage: String? = null
    var baseStatus: String? = null
    var baseResponse: Response<*>? = null
    var baseCode: Int = 0
}