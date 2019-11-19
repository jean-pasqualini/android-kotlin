package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class GetData(
    @SerializedName("origin") val ip: String,
    @SerializedName("url")    val url: String
) {}