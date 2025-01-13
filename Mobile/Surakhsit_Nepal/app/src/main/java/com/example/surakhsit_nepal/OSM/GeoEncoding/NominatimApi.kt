package com.example.osmandroid.OSM.GeoEncoding

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimApi {
    // Geocoding (Address → Coordinates): address halera coordinates line
    @GET("search")
    fun geocode(
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("limit") limit: Int = 1
    ): Call<List<GeocodeResponse>>

    // Reverse Geocoding (Coordinates → Address): coordinates halera address line
    @GET("reverse")
    fun reverseGeocode(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1
    ): Call<ReverseGeocodeResponse>
}
