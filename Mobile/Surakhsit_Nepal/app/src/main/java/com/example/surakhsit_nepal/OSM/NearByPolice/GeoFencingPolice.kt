package com.example.osmandroid.OSM

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.osmandroid.OSM.GeoFencing.MarkerClickedViewModel
import com.example.osmandroid.OSM.GeoFencing.addGeofencesToMap
import com.example.osmandroid.OSM.GeoLiveLocation.LocationViewModel
import com.example.osmandroid.OSM.GeoLiveLocation.drawLiveLocationMarkerCircle
import com.example.osmandroid.OSM.GeoRouting.DirectionsRequest
import com.example.osmandroid.OSM.GeoRouting.fetchRouteAndDisplay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

import androidx.lifecycle.viewmodel.compose.viewModel
import org.osmdroid.views.MapView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoFencingPolice(locationViewModel: LocationViewModel) {
    val location by locationViewModel.currentLocation.collectAsState()
    //var map: org.osmdroid.views.MapView? = null
    var marker: org.osmdroid.views.overlay.Marker? = null
    var shouldRecenterMap = true

    val markerClickedViewModel: MarkerClickedViewModel = viewModel()

    val clickedGeoPoint by markerClickedViewModel.clickedGeoPoint.observeAsState()
    val startButtonVisibility by markerClickedViewModel.isButtonVisible
    val exitButtonVisibility by markerClickedViewModel.isExitButtonVisible
    val routeVisibility by markerClickedViewModel.isRouteVisible
    //Log.d("geopoint", clickedGeoPoint.toString())

    var refreshMap by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var map: MapView = remember { MapView(context) }

    // Manage the lifecycle of the map
    DisposableEffect(context) {
        onDispose {
            map.onDetach()
        }
    }


    Box() {
        AndroidView(
            factory = { context ->
                // suruma load yo factory vitrako hunxa
                map = org.osmdroid.views.MapView(context).apply {
                    setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
                    controller.setZoom(15.0)
                    setMultiTouchControls(true)
                    setBuiltInZoomControls(true)

                    setMapListener(object : org.osmdroid.events.MapListener {
                        override fun onScroll(event: org.osmdroid.events.ScrollEvent?): Boolean {
                            shouldRecenterMap = false
                            return true
                        }

                        override fun onZoom(event: org.osmdroid.events.ZoomEvent?): Boolean {
                            return false
                        }
                    })
                }

                map.let {
                    addGeofencesToMap(it)
                }

                map
            },
            modifier = Modifier.fillMaxWidth()
                .height(340.dp),
            update = {
                // location change vayesi marker matra change garna lai
                location?.let {
                    map?.let { mapView ->

                        //Draw the live location marker circle
                        drawLiveLocationMarkerCircle(
                            mapView,
                            GeoPoint(it.latitude, it.longitude)
                        )


                    }

                    if (shouldRecenterMap) {
                        map?.controller?.animateTo(GeoPoint(it.latitude, it.longitude))
                    }


                }

            },
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom=45.dp)// Padding for the search box
                .align(Alignment.BottomEnd)
        ) {
//            Button(onClick = { shouldRecenterMap = true }) {
//                Text(text="Current Location")
//            }

        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom=45.dp)
                .align(Alignment.BottomEnd)
        ) {
            if (startButtonVisibility) {
                Button(onClick = {
                    markerClickedViewModel.hideButton()
                    markerClickedViewModel.routeVisible()
                    markerClickedViewModel.showExitButton()
                }) {
                    Text(text = "Start")
                }
            }

            if(exitButtonVisibility){
                Button(onClick = {
                    markerClickedViewModel.showButton()
                    markerClickedViewModel.routeInvisible()
                    markerClickedViewModel.hideExitButton()
                }) {
                    Text(text = "Exit")
                }
            }
        }
    }




    // every 2.5 seconds ma location fetch garna lai
    LaunchedEffect(Unit) {
        launch {
            while (true) {
                delay(1500)
                locationViewModel.fetchCurrentLocation()
                Log.d("retrofit", "launcher")
            }
        }
    }

}
