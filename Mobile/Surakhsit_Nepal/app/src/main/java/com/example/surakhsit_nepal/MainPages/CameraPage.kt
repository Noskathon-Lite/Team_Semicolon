package com.example.surakhsit_nepal.MainPages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun CameraPage(navController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)


    var video_file by remember { mutableStateOf<File?>(null) }
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var location by remember { mutableStateOf<Location?>(null) }


    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(context, "Location permission required", Toast.LENGTH_SHORT).show()
    } else {

        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                location = task.result
            } else {
                Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val recordVideoLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            Toast.makeText(context, "Video capture success: $success", Toast.LENGTH_SHORT).show()
        })

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavBar()
            BelowNavBar("Camera Section") {
//                navController.navigate(Screens.mainScreen.route)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Capture video button
            OutlinedButton(
                onClick = {
//                    video_file = context.createVideoFile()
//                    videoUri = video_file?.getUri(context = context)
//
//                    recordVideoLauncher.launch(videoUri)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text(text = "Capture Video")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Send video button
            OutlinedButton(
                onClick = {

//                    val data = atLong(
//                        location_longitude = "90",
//                        location_latitude = "80",
//                        video_file = videoUri
////                    )
//                    scope.launch {
//                        val response = Backend.apiService.sendData(data)
//                        try {
//                            if (response.isSuccessful) {
//                                Toast.makeText(context, "fuck uyou", Toast.LENGTH_LONG).show()
//                            }
//                        }catch(e:Exception){
//                            Toast.makeText(context,"${e.message}",Toast.LENGTH_LONG).show()
//                        }
//                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text(text = "Send Video")
            }

        }
    }
}

//fun uploadVideoWithLocation(context: Context, videoFile: File, location_latitude: Double, location_longitude: Double) {
//
//    uploadVideo(context, videoFile, location_latitude, location_longitude)
//
//}
