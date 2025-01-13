package com.example.osmandroid.OSM.GeoRouting

import android.util.Log
import com.google.maps.android.PolyUtil
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val polylineList = mutableListOf<Polyline>()
fun fetchRouteAndDisplay(
    mapView: MapView,
    coordinates: DirectionsRequest
) {
    Log.d("retrofit", "API request initiated")

    val call = ClientInterfaceGeoRouting.api.getDirections(coordinates)
    call.enqueue(object : Callback<DirectionsResponse> {
        override fun onResponse(
            call: Call<DirectionsResponse>,
            response: Response<DirectionsResponse>
        ) {
            Log.d("retrofit", "Response received: ${response.code()}")
            if (response.isSuccessful) {
                val directionsResponse = response.body()
                if (directionsResponse != null) {
                    Log.d("retrofit", "Successful response received")
                    val route = directionsResponse.routes?.firstOrNull()
                    if (route != null) {
                        Log.d("retrofit", "Route found: ${route.toString()}")

                        // Decode the polyline geometry string into coordinates
                        val routeGeometry = route.geometry // Encoded polyline string
                        val decodedCoordinates = PolyUtil.decode(routeGeometry) // Decode the polyline

                        // Convert the decoded coordinates to GeoPoint (latitude, longitude)
                        val geoPoints = decodedCoordinates.map { GeoPoint(it.latitude, it.longitude) }

                        // Create and add polyline to the map
                        val polyline = Polyline().apply {
                            setPoints(geoPoints)
                            width = 7f // Set polyline thickness
                            color = 0xFF5383EC.toInt() // Set polyline color
                        }
                        if (polylineList.size >= 2) {
                            val oldPolyline = polylineList.removeAt(0) // Remove the first polyline (oldest)
                            mapView.overlays.remove(oldPolyline) // Remove it from the map
                        }

                        polylineList.add(polyline)

                        mapView.overlays.add(polyline)
                        mapView.invalidate()
                    } else {
                        Log.e("retrofit", "No routes found in the response.")
                    }
                } else {
                    Log.e("retrofit", "Response body is empty.")
                }
            } else {
                Log.e("retrofit", "Error: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
            Log.e("OpenRouteService", "Failure: ${t.message}")
            t.printStackTrace()
        }
    })
}
