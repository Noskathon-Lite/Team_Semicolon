package com.example.surakhsit_nepal.CriminalDatabase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surakhsit_nepal.Backend.BackendObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CriminalsViewModel : ViewModel() {
    private val _criminals = MutableStateFlow<List<criminalsItem>>(emptyList())
    val criminals: StateFlow<List<criminalsItem>> = _criminals

    init {
        fetchCriminals()
    }

    private fun fetchCriminals() {
        viewModelScope.launch {
            try {
                // token authorization ko lagi
                val bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4MjA4OTg1LCJpYXQiOjE3MzY2NzI5ODUsImp0aSI6IjY0MThmN2M2ZGZhYjQ1NWNhZGI3YjEzNGJhZDU1MjE1IiwidXNlcl9pZCI6Mn0._dTs6iszpk7sergxY4e-QsX9cIevCq0AZkf68l0RsLg"

                //api call gareko using  token
                val response = BackendObject.authService.fetchData("Bearer $bearerToken")


                Log.d("FetchCriminals", "Response: $response")


                _criminals.value = response
            } catch (e: Exception) {

                e.printStackTrace()
                Log.e("FetchCriminals", "Error: ${e.message}")
            }
        }
    }
}
