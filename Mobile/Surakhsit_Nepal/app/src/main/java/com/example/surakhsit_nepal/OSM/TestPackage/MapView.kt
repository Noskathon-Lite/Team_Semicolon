package com.example.osmandroid.OSM.TestPackage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.osmandroid.OSM.GeoEncoding.geocodeAddress
import com.example.osmandroid.OSM.GeoRouting.DirectionsRequest

import com.example.osmandroid.OSM.GeoRouting.fetchRouteAndDisplay
import com.example.osmandroid.OSM.rememberMapViewWithLifecycle
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

/**
 * A composable Google Map.
 * @author Arnau Mora
 * @since 20211230
 * @param modifier Modifiers to apply to the map.
 * @param onLoad This will get called once the map has been loaded.
 */
//@Composable
//fun MapView(
//    modifier: Modifier = Modifier,
//    onLoad: ((map: MapView) -> Unit)? = null,
//) {
//
//    val mapViewState = rememberMapViewWithLifecycle()
//
//    AndroidView(
//        { mapViewState },
//        modifier
//    ) {
//        mapView ->
//        onLoad?.invoke(mapView)
//        mapView.setTileSource(TileSourceFactory.MAPNIK)
//        mapView.setBuiltInZoomControls(true)
//        mapView.setMultiTouchControls(true)
//
//        val mapController = mapView.controller
//        mapController.setZoom(15)
//        val startPoint = GeoPoint(27.673331, 85.366872) // Example location
//        mapController.setCenter(startPoint)
//
//        // Show current location
//        val locationOverlay = MyLocationNewOverlay(mapView)
//        locationOverlay.enableMyLocation() // Enables location updates
//        mapView.overlays.add(locationOverlay)
//
//        locationOverlay.runOnFirstFix {
//            val currentLocation = locationOverlay.myLocation // Get current location as GeoPoint
//            if (currentLocation != null) {
//                mapView.post {
//                    // Center map on current location
//                    mapView.controller.setCenter(currentLocation)
//
//                    // Add a marker at the current location
//                    val marker = Marker(mapView)
//                    marker.position = currentLocation
//                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
//                    marker.title = "You are here!"
//                    mapView.overlays.add(marker)
//                    mapView.invalidate() // Refresh the map
//                }
//            }
//        }
//        geocodeAddress("Kathmandu, Nepal") { geoPoint ->
//            if (geoPoint != null) {
//                // Center the map on the geocoded location
//                mapView.controller.setCenter(geoPoint)
//
//                // Add a marker at the geocoded location
//                val marker = Marker(mapView)
//                marker.position = geoPoint
//                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
//                marker.title = "Kathmandu, Nepal"
//                mapView.overlays.add(marker)
//                mapView.invalidate() // Refresh the map
//            } else {
//                Log.e("Geocode", "Address not found.")
//            }
//        }
//
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapViewWithSearch(
    modifier: Modifier = Modifier,
    onLoad: ((map: MapView) -> Unit)? = null,
    location: android.location.Location?
) {
    val context = LocalContext.current
    val mapViewState = rememberMapViewWithLifecycle()
    var searchQuery by remember { mutableStateOf("") } // For the search box input
    var isSearching by remember { mutableStateOf(false) } // For showing search progress
    val keyboardController = LocalSoftwareKeyboardController.current

    var startPoint = GeoPoint(27.667286035178197,85.34036122320019)
    LaunchedEffect(location) {
        if (location != null) {
            startPoint = GeoPoint(location.latitude, location.longitude)
            Log.d("CurrentLocation",location.toString())
        }else {
            // Handle the case where location is null (e.g., show a default location or an error message)
            Log.e("CurrentLocation", "Location is null. Unable to create GeoPoint.")
        }
    }

    fun onSearch(){
        isSearching = true
    }



    Box(modifier) {
        // MapView
        AndroidView(
            { mapViewState },
            Modifier.fillMaxWidth()// Give remaining space to the map
        ) { mapView ->
            onLoad?.invoke(mapView)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setBuiltInZoomControls(true)
            mapView.setMultiTouchControls(true)

            val mapController = mapView.controller


            mapController.setZoom(15)

             // Example location
            mapController.setCenter(startPoint)

            // Show current location
            val locationOverlay = MyLocationNewOverlay(mapView)
            locationOverlay.enableMyLocation()
            mapView.overlays.add(locationOverlay)

            //geoFencing(mapView)


            locationOverlay.runOnFirstFix {
                val currentLocation = locationOverlay.myLocation
                Log.d("Map",currentLocation.toString())
                if (currentLocation != null) {
                    mapView.post {
                        mapView.controller.setCenter(currentLocation)

                        val marker = Marker(mapView)
                        marker.position = currentLocation
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        marker.title = "You are here!"
                        mapView.overlays.add(marker)
                        mapView.invalidate()
                    }
                }
            }

            // Perform search when query changes
            var searchedLatitude: Double? = null
            var searchedLongitude: Double? = null

            if (isSearching) {
                geocodeAddress(searchQuery) { geoPoint ->
                    if (geoPoint != null) {
                        // Store the latitude and longitude in variables
                        searchedLatitude = geoPoint.latitude
                        searchedLongitude = geoPoint.longitude

                        Log.d("Geocode", "Latitude: $searchedLatitude, Longitude: $searchedLongitude")

                        mapView.post {
                            // Center the map on the geocoded location
                            mapController.setCenter(geoPoint)

                            // Add a marker at the geocoded location
                            val marker = Marker(mapView)
                            marker.position = geoPoint
                            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            marker.title = searchQuery
                            mapView.overlays.add(marker)
                            mapView.invalidate()
                        }
                    } else {
                        Log.e("Geocode", "Address not found.")
                    }
                }
                isSearching = false
            }


//locationOverlay.runOnFirstFix yesko kaam vaneko chai executes a block of code when the location becomes available for the first time.
            locationOverlay.runOnFirstFix {
                val currentLocation = locationOverlay.myLocation
                var searchedLatitude: Double = 85.34269705887475
                var searchedLongitude: Double? = 27.673920867222417

                if (isSearching) {
                    geocodeAddress(searchQuery) { geoPoint ->
                        if (geoPoint != null) {
                            // Store the latitude and longitude in variables
                            searchedLatitude = geoPoint.latitude
                            searchedLongitude = geoPoint.longitude

                            Log.d("Geocode", "Latitude: $searchedLatitude, Longitude: $searchedLongitude")

                            mapView.post {
                                // Center the map on the geocoded location
                                mapController.setCenter(geoPoint)

                                // Add a marker at the geocoded location
                                val marker = Marker(mapView)
                                marker.position = geoPoint
                                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                marker.title = searchQuery
                                mapView.overlays.add(marker)
                                mapView.invalidate()
                            }
                        } else {
                            Log.e("Geocode", "Address not found.")
                        }
                    }
                    isSearching = false
                }

                if (currentLocation != null) {
                    val directionRequest = DirectionsRequest(
                        coordinates = listOf(
                            listOf(currentLocation.longitude, currentLocation.latitude),  // Start [longitude, latitude]
                            listOf(85.34036122320019,27.667286035178197),

                            //listOf(85.34269705887475, 27.673920867222417)// End [longitude, latitude]
                        )
                    )
                    //fetchRouteAndDisplay(mapView, directionRequest)
                } else {
                    Log.e("Location", "Failed to retrieve location after first fix.")
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(top=25.dp)// Padding for the search box
                .align(Alignment.TopCenter)
        ){
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search location") },
                leadingIcon = {
                    // Search icon for Google Maps-like look
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_search_category_default),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(32.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(50.dp)),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        keyboardController?.hide()
                    }
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedLeadingIconColor = Color.Gray,
                    focusedPrefixColor = Color.Black,
                    unfocusedPrefixColor = Color.Black
                )
            )
        }
    }
}
