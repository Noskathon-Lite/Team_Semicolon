package com.example.surakhsit_nepal.Backend.BackendData


import android.util.Log
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surakhsit_nepal.Backend.BackendObject
import com.example.surakhsit_nepal.Backend.login.LoginRequest
import kotlinx.coroutines.launch


class BackendViewModel(): ViewModel(){
     private val responseMessage  =   mutableStateOf("")


    fun registerUser(
        email : String,
        name : String,
        password : String,
        phone_number : String,
        onResult : (String) -> Unit
    ){
        viewModelScope.launch {
            try{
                val request = userData(email,name,password,phone_number)
                val response = BackendObject.authService.registerUser(request)
                if(response.isSuccessful){
                    val message = response.body()?.message ?: "Registration Successful!"
                    responseMessage.value = message
                    onResult(message)

                }else{
                    val error = response.errorBody()?.string() ?: "Unknown Error"
                    onResult("Error: $error")
                    Log.e("registration",error)
                }

            }catch(e : Exception){
                onResult("Registration Failed: ${e.message}")
                Log.e("registration","${e.message}")

            }
        }

    }



    fun loginUser(phone_number: String,password : String,onResult: (String) -> Unit){
        viewModelScope.launch {
            try{
                val request = LoginRequest(phone_number = phone_number,password = password)
                val response = BackendObject.authService.loginUser(request)
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    val accessToken = loginResponse?.access_token ?: ""
                    val refresh_token = loginResponse?.refresh_token?: ""

//                    SaveAccessToken(accessToken)
//                    SaveRefreshToken(refresh_token)
                }else{
                    val error = response.errorBody()?.string() ?: "Unknown Error"
                    onResult("Login Failed: $error")
                }

            }catch(e: Exception){
                onResult("Login Failed: ${e.message}")
            }
        }
    }
}