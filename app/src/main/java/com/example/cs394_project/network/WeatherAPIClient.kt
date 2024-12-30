package com.example.cs394_project.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WeatherAPIClient {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
}