package com.example.testapplication

import com.example.testapplication.model.Car

interface DataSource {

    suspend fun getCars(): ApiResult<List<Car>>
}