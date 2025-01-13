package com.example.osmandroid.OSM.GeoRoutingOSRM

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline


fun getRoute(mapView: MapView, start: GeoPoint, end: GeoPoint) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            // Fetch route
            val response = RetrofitInstance.api.getRoute(

                start.longitude,
                start.latitude,
                start.longitude,
                start.latitude
            )

            if (response.routes.isEmpty()) {
                Log.e("OSRM", "No routes found. Check snapped points or API server.")
                return@launch
            }

            // Parse coordinates and plot route
            val coordinates = response.routes[0].geometry.coordinates
            val routePoints = coordinates.map { GeoPoint(it[1], it[0]) }
            launch(Dispatchers.Main) {
                plotRoute(mapView, routePoints)
            }
        } catch (e: Exception) {
            Log.e("OSRM", "Error fetching route: ${e.localizedMessage}")
        }
    }
}



private fun plotRoute(mapView: MapView, routePoints: List<GeoPoint>) {
    // Create a polyline to display on the map
    Log.d("hello1","hello1")
    val polyline = Polyline()
    polyline.setPoints(routePoints)
    polyline.color = 0xFF0000FF.toInt() // Blue color for the route
    mapView.overlayManager.add(polyline)
    mapView.invalidate()  // Refresh the map view to show the new route
}