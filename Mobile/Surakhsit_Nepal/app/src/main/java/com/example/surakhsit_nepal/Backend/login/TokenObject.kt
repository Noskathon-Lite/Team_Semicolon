package com.example.surakhsit_nepal.Backend.login


import com.example.surakhsit_nepal.Backend.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TokenObject {

    private const val BASE_URL = "http://192.168.23.8:8000/api/"

    // Function to provide an OkHttpClient with a token interceptor
    private fun provideOkHttpClient(tokenProvider: () -> String?): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor { chain ->
            val request = chain.request()
            val token = tokenProvider() // Get the token dynamically
            val authenticatedRequest = if (token != null && token.isNotBlank()) {
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                request
            }
            chain.proceed(authenticatedRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    // Lazy initialization of Retrofit with a token provider
    fun getRetrofit(tokenProvider: () -> String?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(tokenProvider))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazy initialization of ApiInterface
    fun getAuthService(tokenProvider: () -> String?): ApiInterface {
        return getRetrofit(tokenProvider).create(ApiInterface::class.java)
    }
}
