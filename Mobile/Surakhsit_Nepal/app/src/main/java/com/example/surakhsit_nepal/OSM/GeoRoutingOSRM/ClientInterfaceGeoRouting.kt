package com.example.osmandroid.OSM.GeoRoutingOSRM

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://router.project-osrm.org/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OSRMApi = retrofit.create(OSRMApi::class.java)
}
