package com.example.osmandroid.NearByPolice

import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.osmandroid.OSM.GeoFencing.nearByPoliceStations
import com.example.osmandroid.OSM.GeoFencingPolice
import com.example.osmandroid.OSM.GeoLiveLocation.LocationViewModel

@Composable
fun NearByPolice(navController : NavHostController, locationViewModel: LocationViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        nearByPoliceStations.forEach { station ->
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)) // Border with rounded corners
                    .padding(16.dp)
                    .clickable {

                    }
            ) {
                Column {
                    Text(
                        text = station["name"] as String,
                        modifier = Modifier.padding(bottom = 8.dp) // Space between name and distance
                    )
                    Text(text = station["distance"] as String)
                }
            }
        }
        Spacer(Modifier.height(80.dp))
        Box(
            Modifier.height(320.dp)
                .fillMaxWidth()
        ){
            GeoFencingPolice(locationViewModel)
        }
    }
}
