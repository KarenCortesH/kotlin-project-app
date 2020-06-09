package com.example.myapplication.services
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://b81d5687317e.ngrok.io/api/") // change this IP for testing by your actual machine IP
    //https://a2d9d157fdc2.ngrok.io  **"https://de46658007af.ngrok.io/api/"
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}