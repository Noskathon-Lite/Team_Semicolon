package com.example.osmandroid.OSM.GeoFencing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.osmdroid.util.GeoPoint

class MarkerClickedViewModel : ViewModel() {
    private val _clickedGeoPoint = MutableLiveData<GeoPoint?>(null)
    val clickedGeoPoint: LiveData<GeoPoint?> = _clickedGeoPoint

    fun updateClickedGeoPoint(geoPoint: GeoPoint) {
        _clickedGeoPoint.value = geoPoint
    }


    //button aaos vanna ko lagi
    private val _isButtonVisible = mutableStateOf(false)
    val isButtonVisible: State<Boolean> = _isButtonVisible

    fun showButton() {
        _isButtonVisible.value = true
    }

    fun hideButton() {
        _isButtonVisible.value = false
    }

    //exit
    private val _isExitButtonVisible = mutableStateOf(false)
    val isExitButtonVisible: State<Boolean> = _isExitButtonVisible

    fun showExitButton() {
        _isExitButtonVisible.value = true
    }

    fun hideExitButton() {
        _isExitButtonVisible.value = false
    }


    //route
    private val _isRouteVisible = mutableStateOf(false)
    val isRouteVisible: State<Boolean> = _isRouteVisible

    fun routeVisible() {
        _isRouteVisible.value = true
    }

    fun routeInvisible() {
        _isRouteVisible.value = false
    }
}
