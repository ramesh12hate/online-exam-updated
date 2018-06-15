package com.embibe.iibnanded.rest

import com.embibe.iibnanded.model.LoginModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET("studentLogin.php")
    fun validateLogin(@Query("username") userName: String, @Query("password") password: String): Call<LoginModel>

}