package com.example.cs394_project.network

import com.example.cs394_project.network.model.WeatherAPIForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    // For city-based queries
    @GET("v1/forecast.json")
    suspend fun getWeatherApiForecast(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Response<WeatherAPIForecast>
}