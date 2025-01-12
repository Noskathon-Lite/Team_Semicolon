package com.example.surakhsit_nepal.Backend

import com.example.surakhsit_nepal.Backend.BackendData.Responses
import retrofit2.Call
import com.example.surakhsit_nepal.Backend.BackendData.userData
import com.example.surakhsit_nepal.Backend.login.LoginRequest
import com.example.surakhsit_nepal.Backend.login.LoginResponse
import com.example.surakhsit_nepal.CriminalDatabase.criminalsItem
import com.example.surakhsit_nepal.feedbackTest.FeedbackRequest
import com.example.surakhsit_nepal.feedbackTest.FeedbackResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {

    //function to register user to connect with backend

    //user registration
    @POST("user/register/")
   suspend fun registerUser(@Body request : userData)  : Response<Responses>

    //user login
    @POST("user/login/")
    suspend fun loginUser(@Body information: LoginRequest) :Response<LoginResponse>

    //video ko lagi
    @Multipart
    @POST("lat/")
    suspend fun uploadVideo(
        @Part video_file: MultipartBody.Part
    ): Response<ResponseBody>

    //feedback ko lagi
    @POST("create/feedback/")
    suspend fun sendFeedback(
        @Body feedback: FeedbackRequest
    ): Response<FeedbackResponse>

    //criminal ko
    @GET("list/criminal/")
    suspend fun fetchData(
        @Header("Authorization") token: String
    ): List<criminalsItem>
}