package com.example.osmandroid.OSM.GeoFencing

import android.util.Log
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polygon


// Function to add geofences
fun addGeofencesToMap(mapView: MapView){
    var clickedGeoPoint: GeoPoint? = null
    nearByPoliceStations.forEach { geofence ->
        val latitude = geofence["latitude"] as Double
        val longitude = geofence["longitude"] as Double
        val name = geofence["name"] as String

        drawGeofenceCircle(mapView, GeoPoint(latitude, longitude), 200.0, name)

    }

}

// Function to draw a single geofence circle with a marker
fun drawGeofenceCircle(
    mapView: MapView,
    center: GeoPoint,
    radiusMeters: Double,
    popupText: String,
){
    // Create the circle
    var clickedGeoPoint: GeoPoint? = null
    val circle = Polygon(mapView).apply {
        points = Polygon.pointsAsCircle(center, radiusMeters)
        fillColor = 0x12121212 // Semi-transparent fill
        strokeColor = 0xFF5383EC.toInt() // Blue outline
        strokeWidth = 2.0f
        infoWindow = null
    }

    // Add circle to the map
    mapView.overlayManager.add(circle)

    // Create and configure a marker for the geofence center
    val marker = Marker(mapView).apply {
        position = center
        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        title = popupText
        setOnMarkerClickListener { m, _ ->
            m.showInfoWindow() // Show popup text when the marker is clicked
            val markerLat = position.latitude
            val markerLon = position.longitude

            clickedGeoPoint = GeoPoint(markerLat,markerLon)
//            markerClickedViewModel.updateClickedGeoPoint(clickedGeoPoint!!)
//            markerClickedViewModel.showButton()
//            markerClickedViewModel.hideExitButton()
//            markerClickedViewModel.routeVisible()
            Log.d("marker", clickedGeoPoint.toString())

            true
        }
    }


    // Add the marker to the map
    mapView.overlays.add(marker)

    // Refresh the map to display updates
    mapView.invalidate()

}
