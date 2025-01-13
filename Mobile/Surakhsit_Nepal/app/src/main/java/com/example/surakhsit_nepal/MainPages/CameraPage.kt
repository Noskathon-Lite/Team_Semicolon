package com.example.surakhsit_nepal.MainPages

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.MainPages.videoUploader.uploadVideo
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun CameraPage(navController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var video_file by remember { mutableStateOf<File?>(null) }
    var location by remember { mutableStateOf<Location?>(null) }

    //video ko lagi
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4MjM4NjY3LCJpYXQiOjE3MzY3MDI2NjcsImp0aSI6ImIzOTQ0M2M2ZmY2ZDQ2ZDk4YzVkOWVkN2EzYzZmNDUyIiwidXNlcl9pZCI6MX0.h6spFQ38T0LGD74k_LDvEYtOWvLcNKHMZ64JNazE-so"
    val latitude = "12.345678"
    val longitude = "98.765432"

    // video recording garne kaam yesle garxa
    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            Toast.makeText(context, "Video saved to: $videoUri", Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.IO).launch {
                videoUri?.let {
                    uploadVideo(context, it, token, latitude, longitude)
                }
            }
        } else {
            Toast.makeText(context, "Video recording failed or canceled", Toast.LENGTH_SHORT).show()
        }
    }

    // storage bata video lyauna laii
    val filePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            CoroutineScope(Dispatchers.IO).launch {
                uploadVideo(context, it, token, latitude, longitude)
            }
        }
    }





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

            Spacer(modifier = Modifier.height(140.dp))

            // Capture video button
            OutlinedButton(
                onClick = {
                    videoUri = createVideoUri(context)
                    val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).apply {
                        putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
                    }
                    videoLauncher.launch(videoIntent)
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

                    filePickerLauncher.launch("video/*")
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

fun createVideoUri(context: Context): Uri? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "video_${System.currentTimeMillis()}.mp4")
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
        }
        context.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
    } else {
        val videoDirectory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
            "MyAppVideos"
        )
        if (!videoDirectory.exists()) videoDirectory.mkdirs()
        val videoFile = File(videoDirectory, "video_${System.currentTimeMillis()}.mp4")
        Uri.fromFile(videoFile)
    }
}


@Preview(showBackground = true)
@Composable
fun viewShow(){
    val navController = rememberNavController()
    CameraPage(navController)
}
