package com.example.myapplication.http

import retrofit2.Call
import retrofit2.http.GET

interface HttpBinServiceString {

    @GET("user-agent")
    fun getUserAgent() : Call<String>
}