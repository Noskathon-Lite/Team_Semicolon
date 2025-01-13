package com.example.osmandroid.OSM.GeoRouting

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenRouteServiceApi {
    @POST("v2/directions/foot-walking/json")
    fun getDirections(
        @Body request: DirectionsRequest
    ): Call<DirectionsResponse>
}
