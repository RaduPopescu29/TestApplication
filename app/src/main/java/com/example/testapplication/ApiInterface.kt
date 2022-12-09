package com.example.testapplication

import com.example.testapplication.model.Car
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("b/GI5C")
    suspend fun getCars(): Response<List<Car>>
}