package com.example.testapplication


import android.util.Log
import com.example.testapplication.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RestApiClient {

    val apiClient: ApiInterface by lazy {
        Log.d("rest_client","lazy initialization")
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor).build()


        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient).build()
        return@lazy retrofit.create()
    }


}