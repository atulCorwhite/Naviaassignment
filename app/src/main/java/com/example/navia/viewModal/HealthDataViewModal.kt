package com.example.navia.viewModal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.data.repository.HomeRepository
import com.example.navia.data.remote.modal.HealthData

class HealthDataViewModal : ViewModel() {
    private var data: MutableLiveData<HealthData>? = null
    private val dataRepository = HomeRepository()
    fun init() {
        data = dataRepository.getData()
    }
    fun getData() : MutableLiveData<HealthData>? {
        return data
    }

}