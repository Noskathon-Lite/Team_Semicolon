package com.example.osmandroid.OSM.GeoEncoding

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//yesko main use vaneko Nominatim api lai centralized way maa reuse garna dine ho
object ClientInterfaceGeoEncoding {
    private const val BASE_URL = "https://nominatim.openstreetmap.org/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "OSMAndroid/1.0") //value maa aafno app/project ko name
                .build()
            chain.proceed(request)
        }
        .build()

    val api: NominatimApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NominatimApi::class.java)
    }
}
