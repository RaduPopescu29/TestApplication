package com.example.testapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.model.Car
import kotlinx.coroutines.launch
import retrofit2.Response

class CarsViewModel() : ViewModel() {

    val carListLiveData: MutableLiveData<List<Car>> = MutableLiveData()
    val repository = NetworkRepository.invoke(RestApiClient.apiClient)

    fun getCars() {

        Log.d("rest_client","getCars called in viewModel")
        viewModelScope.launch {
            val response = repository.getCars()
            when (response) {
                is ApiResult.Success -> carListLiveData.value = response.data
                is ApiResult.Unauthorized -> {}
                is ApiResult.Exception -> {}
                is ApiResult.Error -> {}
                is ApiResult.OtherException->{}
            }
        }
    }
}
