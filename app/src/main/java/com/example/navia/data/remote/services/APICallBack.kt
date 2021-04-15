package com.example.navia.data.remote.services

import com.example.navia.data.remote.modal.HealthData
import retrofit2.Call
import retrofit2.http.GET

interface APICallBack {
    @GET("/dummy/")
    fun getHealthDataResponse(): Call<HealthData>
}