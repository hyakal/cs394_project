package com.example.cs394_project.repository

import com.example.cs394_project.network.WeatherAPIClient
import com.example.cs394_project.network.model.WeatherAPIForecast
import retrofit2.Response

class WeatherRepository {

    suspend fun getForecastByCity(
        apiKey: String,
        cityName: String,
        days: Int
    ): Response<WeatherAPIForecast> {
        return WeatherAPIClient.service.getWeatherApiForecast(
            apiKey = apiKey,
            query = cityName,
            days = days
        )
    }
}