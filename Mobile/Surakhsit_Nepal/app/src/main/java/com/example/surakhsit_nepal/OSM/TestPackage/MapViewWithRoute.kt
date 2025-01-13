package com.example.osmandroid.OSM.TestPackage

import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.osmandroid.OSM.GeoLiveLocation.LocationViewModel
import com.example.osmandroid.OSM.GeoLiveLocation.drawLiveLocationMarkerCircle
import com.example.osmandroid.OSM.GeoRoutingOSRM.RetrofitInstance
import com.example.osmandroid.OSM.rememberMapViewWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

@Composable
fun MapViewWithRoute(
    locationViewModel: LocationViewModel,
) {
    var location by remember { mutableStateOf<Location?>(null) }
    var routePoints by remember { mutableStateOf<List<GeoPoint>>(emptyList()) }
    val mapViewState = rememberMapViewWithLifecycle()
    var marker: org.osmdroid.views.overlay.Marker? = null
    var shouldRecenterMap = true
    val context = LocalContext.current
    val start = GeoPoint(85.3667111563022,27.67331300754723 )
    val end = GeoPoint(85.34954501930572, 27.67863375424564 )

    val map = remember {
        org.osmdroid.views.MapView(context).apply {
            setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
            controller.setZoom(15.0)
            setMultiTouchControls(true)
            setBuiltInZoomControls(true)

            setMapListener(object : org.osmdroid.events.MapListener {
                override fun onScroll(event: org.osmdroid.events.ScrollEvent?): Boolean {
                    shouldRecenterMap = false // User scrolled the map, disable auto-recenter
                    return true
                }

                override fun onZoom(event: org.osmdroid.events.ZoomEvent?): Boolean {
                    return false
                }
            })
        }
    }

    // Fetch route when start or end points change
    LaunchedEffect(start, end) {
        getRoute(map, start, end) { fetchedRoutePoints ->
            routePoints = fetchedRoutePoints
        }
    }

    // Fetch location every 2.5 seconds
    LaunchedEffect(Unit) {
        while (true) {
            locationViewModel.fetchCurrentLocation()
            delay(2500)
        }
    }

    AndroidView(
        factory = { context ->
            map.apply {
                // Suruko marker
                marker = org.osmdroid.views.overlay.Marker(this).apply {
                    setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM)
                    overlayManager.add(this)
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = {
            // Update location if available
            location?.let {
                map.apply {
                    drawLiveLocationMarkerCircle(
                        this,
                        GeoPoint(it.latitude, it.longitude)
                    )
                    if (shouldRecenterMap) {
                        controller.animateTo(GeoPoint(it.latitude, it.longitude))
                    }
                }
            }

            // Draw route if fetched
            if (routePoints.isNotEmpty()) {
                plotRoute(map, routePoints)
            }
        }
    )
}

fun getRoute(mapView: MapView, start: GeoPoint, end: GeoPoint, onRouteFetched: (List<GeoPoint>) -> Unit) {
    // Network call to fetch route (use your existing logic)
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getRoute(
                start.latitude, start.longitude, end.latitude, end.longitude
            )

            val routePoints = ArrayList<GeoPoint>()
            val coordinates = response.routes[0].geometry.coordinates
            for (point in coordinates) {
                val lon = point[0]
                val lat = point[1]
                routePoints.add(GeoPoint(lat, lon))
            }

            // On route fetched, update the UI on the main thread
            launch(Dispatchers.Main) {
                onRouteFetched(routePoints)
            }
        } catch (e: Exception) {
            Log.e("OSRM", "Error fetching route: ${e.localizedMessage}")
        }
    }
}

fun plotRoute(mapView: MapView, routePoints: List<GeoPoint>) {
    val polyline = Polyline().apply {
        setPoints(routePoints)
        color = 0xFF0000FF.toInt() // Blue color for the route
    }
    mapView.overlayManager.add(polyline)
    mapView.invalidate()  // Refresh the map view to show the new route
}
