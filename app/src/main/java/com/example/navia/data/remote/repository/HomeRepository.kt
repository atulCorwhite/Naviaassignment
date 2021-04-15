package com.example.demoproject.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.navia.data.remote.modal.HealthData
import com.example.navia.data.remote.services.APICallBack
import com.example.navia.data.remote.services.ApiClient
import retrofit2.Call
import retrofit2.Response

class HomeRepository {
    private lateinit var apiInterface: APICallBack

    fun getData(): MutableLiveData<HealthData>? {
        var dataModel=MutableLiveData<HealthData>()
        apiInterface = ApiClient.getInstance()?.create(APICallBack::class.java)
    val call = apiInterface?.getHealthDataResponse()
    call?.enqueue(object : retrofit2.Callback<HealthData> {
        override fun onFailure(call: Call<HealthData>, t: Throwable) {
            Log.e("exception", t.message!!)
        }

        override fun onResponse(call: Call<HealthData>, response: Response<HealthData>) {
            if (response.isSuccessful) {
                dataModel.postValue(response.body())
            }
        }
    })

   return dataModel }

}