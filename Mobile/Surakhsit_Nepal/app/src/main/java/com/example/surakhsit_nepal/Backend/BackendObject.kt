package com.example.surakhsit_nepal.Backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendObject {

    val BASE_URL = "http://0.0.0.0:8000/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiInterface::class.java)
}