package com.embibe.iibnanded.network.client

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by IoT-Engg team on 11/06/18.
 */
object RetrofitClient {
    private val BASE_URL = "http://codingbit.co.in/institute_online_exam/API/"
    private val OKHTTP_CLIENT_TIMEOUT = 60

    class Builder {
        private var okHttpClient: OkHttpClient? = null
        private var jacksonConverterFactory: JacksonConverterFactory? = null


        private val simpleHttpClient: OkHttpClient
            get() {
                val httpClient = OkHttpClient.Builder()
                httpClient.connectTimeout(OKHTTP_CLIENT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                httpClient.readTimeout(OKHTTP_CLIENT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                httpClient.followRedirects(false)
                httpClient.addInterceptor(HeaderInterceptor())
                return httpClient.build()
            }

        fun buildRequest(): Retrofit {
            okHttpClient = simpleHttpClient
            jacksonConverterFactory = getJacksonConverterFactory()
            return Retrofit.Builder()//.client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(jacksonConverterFactory!!)
                    .build()
        }

        private fun getJacksonConverterFactory(): JacksonConverterFactory {
            val objectMapper = ObjectMapper()
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            return JacksonConverterFactory.create(objectMapper)
        }

        private inner class HeaderInterceptor : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                val newRequest = request.newBuilder()
                        .addHeader("application/json", "application/json")
                        .build()
                return chain.proceed(newRequest)
            }
        }
    }
}
