package com.example.osmandroid.OSM.TestPackage

import com.example.osmandroid.OSM.GeoFencing.nearByPoliceStations
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polygon

val geofenceLat = 27.680660595989508
val geofenceLon = 85.34952349923194


fun geoFencing(mapView: MapView){
    mapView.setTileSource(TileSourceFactory.BASE_OVERLAY_NL)
    mapView.setBuiltInZoomControls(true)
    //mapView.isMultiTouchControls = true


    // Use the bounding box to zoom
    mapView.controller.setZoom(15.0)
    val center = GeoPoint(geofenceLat, geofenceLon)
    mapView.controller.setCenter(center)

    // Draw a circle with a radius of 500 meters
    nearByPoliceStations.forEach { station ->
        drawGeofenceCircle1(mapView, GeoPoint(station["latitude"] as Double,station["longitude"] as Double), 250.0, station["name"] as String)
    }

}

fun drawGeofenceCircle1(
    mapView: MapView,
    center: GeoPoint,
    radiusMeters: Double,
    popupText: String
) {
    // Create the circle
    val circle = Polygon(mapView)
    circle.points = Polygon.pointsAsCircle(center, radiusMeters)
    circle.fillColor = 0x12121212 // Semi-transparent fill
    circle.strokeColor = 0xFF0000FF.toInt() // Blue outline
    circle.strokeWidth = 2.0f

    // Add circle to map
    mapView.overlayManager.add(circle)

    // Add a marker to display popup text
    val marker = Marker(mapView)
    marker.position = center
    //marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    marker.title = popupText // Set the popup text

    // Enable info window for the marker
    marker.setOnMarkerClickListener { m, _ ->
        m.showInfoWindow() // Show popup when clicked
        true
    }

    // Add marker to map
    mapView.overlays.add(marker)

    // Refresh the map
    mapView.invalidate()
}




//private fun isInsideGeofence(userLat: Double, userLon: Double): Boolean {
//    val results = FloatArray(1)
//    Location.distanceBetween(userLat, userLon, geofenceLat, geofenceLon, results)
//    val distanceInMeters = results[0]
//    return distanceInMeters <= geofenceRadius
//}