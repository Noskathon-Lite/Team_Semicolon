package com.example.osmandroid.OSM.GeoEncoding

import org.osmdroid.util.GeoPoint
import retrofit2.Call

fun geocodeAddress(address: String, onResult: (GeoPoint?) -> Unit) {
    val call = ClientInterfaceGeoEncoding.api.geocode(address)
    call.enqueue(object : retrofit2.Callback<List<GeocodeResponse>> {
        override fun onResponse(
            call: Call<List<GeocodeResponse>>,
            response: retrofit2.Response<List<GeocodeResponse>>
        ) {
            if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                val result = response.body()!![0]
                val geoPoint = GeoPoint(result.lat.toDouble(), result.lon.toDouble())
                onResult(geoPoint)
            } else {
                onResult(null) // No results
            }
        }

        override fun onFailure(call: Call<List<GeocodeResponse>>, t: Throwable) {
            t.printStackTrace()
            onResult(null) // Error handling
        }
    })
}


fun reverseGeocode(lat: Double, lon: Double, onResult: (String?) -> Unit) {
    val call = ClientInterfaceGeoEncoding.api.reverseGeocode(lat, lon)
    call.enqueue(object : retrofit2.Callback<ReverseGeocodeResponse> {
        override fun onResponse(
            call: Call<ReverseGeocodeResponse>,
            response: retrofit2.Response<ReverseGeocodeResponse>
        ) {
            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                onResult(result.display_name) // Return the full address
            } else {
                onResult(null) // No results
            }
        }

        override fun onFailure(call: Call<ReverseGeocodeResponse>, t: Throwable) {
            t.printStackTrace()
            onResult(null) // Error handling
        }
    })
}

