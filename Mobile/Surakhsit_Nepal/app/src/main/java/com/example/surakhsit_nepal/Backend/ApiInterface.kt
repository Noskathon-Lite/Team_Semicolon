package com.example.surakhsit_nepal.Backend

import com.example.surakhsit_nepal.Backend.BackendData.Responses
import retrofit2.Call
import com.example.surakhsit_nepal.Backend.BackendData.userData
import com.example.surakhsit_nepal.Backend.login.LoginRequest
import com.example.surakhsit_nepal.Backend.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    //function to register user to connect with backend

    @POST("user/register/")
   suspend fun registerUser(@Body request : userData)  : Response<Responses>


    @POST("user/login/")
    suspend fun loginUser(@Body information: LoginRequest) :Response<LoginResponse>
}