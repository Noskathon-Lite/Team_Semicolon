package com.example.surakhsit_nepal.Backend.login

data class LoginResponse(
    val message : String,
    val user_detail :data,
    val access_token : String,
    val refresh_token : String
)
