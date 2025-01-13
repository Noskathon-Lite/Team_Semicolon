package com.example.osmandroid.OSM.GeoRouting

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientInterfaceGeoRouting {
    private const val BASE_URL = "https://api.openrouteservice.org/"
    private const val API_KEY = "5b3ce3597851110001cf6248237f39f6541644f9a79377c7b3306f59"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer $API_KEY")
                .build()
            chain.proceed(request)
        }
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    // Create Retrofit instance with the custom client
    val api: OpenRouteServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Attach the OkHttpClient here
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenRouteServiceApi::class.java)
    }
}