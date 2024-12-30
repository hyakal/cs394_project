package com.example.cs394_project.network.model

import com.squareup.moshi.Json

data class WeatherAPIForecast(
    val location: Location?,
    val current: Current?,
    val forecast: Forecast?
)

data class Location(
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?
)

data class Current(
    @Json(name = "temp_c") val tempC: Double?,
    val condition: Condition?
)

data class Forecast(
    val forecastday: List<ForecastDay>?
)

data class ForecastDay(
    val date: String?,
    val day: Day?,
    val hour: List<Hour>?
)

data class Day(
    @Json(name = "maxtemp_c") val maxTempC: Double?,
    @Json(name = "mintemp_c") val minTempC: Double?,
    val condition: Condition?
)

data class Hour(
    val time: String?,  // e.g. "2024-12-31 03:00"
    @Json(name = "temp_c") val tempC: Double?,
    val condition: Condition?
)

data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?
)