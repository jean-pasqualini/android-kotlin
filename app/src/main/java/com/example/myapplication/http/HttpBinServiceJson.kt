package com.example.myapplication.http

import com.example.myapplication.model.GetData
import retrofit2.Call
import retrofit2.http.GET

interface HttpBinServiceJson {
    @GET("get")
    fun getUserInfo(): Call<GetData>
}