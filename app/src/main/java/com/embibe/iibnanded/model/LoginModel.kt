package com.embibe.iibnanded.model

import com.google.gson.annotations.SerializedName

data class LoginModel(@SerializedName("ResponseCode") val responseCode: Int,
                      @SerializedName("ResponseMessage") val responseMessage: String)