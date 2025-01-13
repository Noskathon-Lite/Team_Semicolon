package com.example.surakhsit_nepal.MainPages.videoUploader


import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.surakhsit_nepal.Backend.BackendObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

suspend fun uploadVideo(context: Context, videoUri: Uri, token: String, latitude: String, longitude: String) {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(videoUri)
    val videoBytes = inputStream?.readBytes()

    if (videoBytes != null) {
        val requestFile = RequestBody.create("video/mp4".toMediaTypeOrNull(), videoBytes)
        val videoPart = MultipartBody.Part.createFormData("video_file", "video.mp4", requestFile)

        // Create RequestBody for additional fields
        val latitudeBody = RequestBody.create("text/plain".toMediaTypeOrNull(), latitude)
        val longitudeBody = RequestBody.create("text/plain".toMediaTypeOrNull(), longitude)

        try {
            val authHeader = "Bearer $token"
            val response = BackendObject.authService.uploadVideo(
                token = authHeader,
                video = videoPart,
                latitude = latitudeBody,
                longitude = longitudeBody

            )

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Video uploaded successfully!", Toast.LENGTH_SHORT).show()
                }
                Log.d("Upload", "Success: ${response.body()?.string()}")
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Upload failed. Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
                Log.e("Upload", "Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
            Log.e("Upload", "Exception: ${e.message}")
        }
    } else {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to read video file!", Toast.LENGTH_SHORT).show()
        }
        Log.e("Upload", "Failed to read video URI")
    }
}