package com.example.surakhsit_nepal.Backend

import retrofit2.Call
import com.example.surakhsit_nepal.Backend.BackendData.userData
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    //function to register user to connect with backend

    @POST("user/register/")
    fun registerUser(@Body request : userData)  : Call<Unit>
}