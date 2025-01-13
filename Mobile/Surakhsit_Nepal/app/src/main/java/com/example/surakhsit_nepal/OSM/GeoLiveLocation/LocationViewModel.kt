package com.example.osmandroid.OSM.GeoLiveLocation

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation = _currentLocation.asStateFlow()

    fun fetchCurrentLocation() {
        viewModelScope.launch {
            try {
                val location: Location? = fusedLocationClient.getCurrentLocation(
                    com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                ).await() // wait garera location line
                _currentLocation.value = location
            } catch (e: SecurityException) {
                Log.e("CurrentLocation",e.toString())
            } catch (e: Exception) {
                Log.e("CurrentLocation",e.toString())
            }
        }
    }
}
