package com.example.surakhsit_nepal.MainPages.videoUploader


import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@Composable
//fun VideoUploaderScreen(context: Context) {
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        uri?.let {
//            CoroutineScope(Dispatchers.IO).launch {
//                uploadVideo(context, it)
//            }
//        }
//    }
//
//    Button(
//        onClick = { launcher.launch("video/*")},
//        modifier = Modifier.padding(top = 200.dp)
//    ){
//        Text("Upload Video")
//    }
//}
