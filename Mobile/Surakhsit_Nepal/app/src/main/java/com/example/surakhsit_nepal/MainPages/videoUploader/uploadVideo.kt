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


suspend fun uploadVideo(context: Context, videoUri: Uri) {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(videoUri)
    val videoBytes = inputStream?.readBytes()

    if (videoBytes != null) {
        // Create a RequestBody for the video file
        val requestFile = RequestBody.create("video/mp4".toMediaTypeOrNull(), videoBytes)

        // Use "video_file" as the key
        val videoPart = MultipartBody.Part.createFormData("video_file", "video.mp4", requestFile)

        try {
            val response = BackendObject.authService.uploadVideo(videoPart)

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Video uploaded successfully!", Toast.LENGTH_SHORT).show()
                }
                Log.d("Upload", "Success: ${response.body()?.string()}")
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to upload video. Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
                Log.e("Upload", "Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Failed to upload video. Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
            Log.e("Upload", "Exception: ${e.message}")
        }
    } else {
        Log.e("Upload", "Failed to read video URI")
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to read video file!", Toast.LENGTH_SHORT).show()
        }
    }
}
