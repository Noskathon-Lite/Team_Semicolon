package com.example.surakhsit_nepal.Backend

import com.example.surakhsit_nepal.Backend.BackendData.Responses
import retrofit2.Call
import com.example.surakhsit_nepal.Backend.BackendData.userData
import com.example.surakhsit_nepal.Backend.login.LoginRequest
import com.example.surakhsit_nepal.Backend.login.LoginResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {

    //function to register user to connect with backend

    @POST("user/register/")
   suspend fun registerUser(@Body request : userData)  : Response<Responses>


    @POST("user/login/")
    suspend fun loginUser(@Body information: LoginRequest) :Response<LoginResponse>

    @Multipart
    @POST("lat/")
    suspend fun uploadVideo(
        @Part video_file: MultipartBody.Part
    ): Response<ResponseBody>
}