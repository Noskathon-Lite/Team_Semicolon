package com.example.osmandroid.OSM.GeoLiveLocation

import com.example.osmandroid.OSM.GeoFencing.nearByPoliceStations


import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import org.osmdroid.views.overlay.Overlay


var liveLocationOverlay: Overlay? = null

fun drawLiveLocationMarkerCircle(
    mapView: MapView,
    geoPoint: GeoPoint,
) {

    liveLocationOverlay?.let {
        mapView.overlays.remove(it)
    }

    liveLocationOverlay = object : Overlay() {
        override fun draw(c: Canvas, osmv: MapView, shadow: Boolean) {
            val mapProjection = osmv.projection
            val screenCenter = Point()
            mapProjection.toPixels(geoPoint, screenCenter)

            val outerCircle = Paint().apply {
                color = 0xFF5383EC.toInt()
                style = Paint.Style.FILL_AND_STROKE
                alpha = 80
                strokeWidth = 5f
            }
            c.drawCircle(
                screenCenter.x.toFloat(),
                screenCenter.y.toFloat(),
                25.0F,
                outerCircle
            )

            val middleCircle = Paint().apply {
                color = Color.WHITE
                style = Paint.Style.FILL_AND_STROKE
                alpha = 255
                strokeWidth = 5f
            }
            c.drawCircle(
                screenCenter.x.toFloat(),
                screenCenter.y.toFloat(),
                13.0F,
                middleCircle
            )

            val innerCircle = Paint().apply {
                color = 0xFF5383EC.toInt()
                style = Paint.Style.FILL_AND_STROKE
                alpha = 255
                strokeWidth = 5f
            }
            c.drawCircle(
                screenCenter.x.toFloat(),
                screenCenter.y.toFloat(),
                10.0F,
                innerCircle
            )
        }
    }

    // Add the overlay to the map
    liveLocationOverlay?.let {
        mapView.overlays.add(it)
    }

    // Refresh the map
    mapView.invalidate()
}
