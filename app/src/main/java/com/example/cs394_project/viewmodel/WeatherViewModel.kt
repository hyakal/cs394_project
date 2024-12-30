package com.example.cs394_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs394_project.R
import com.example.cs394_project.model.DailyWeather
import com.example.cs394_project.model.HourlyWeather
import com.example.cs394_project.network.model.Hour
import com.example.cs394_project.network.model.WeatherAPIForecast
import com.example.cs394_project.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> get() = _cityName

    private val _weatherCondition = MutableLiveData<String>()
    val weatherCondition: LiveData<String> get() = _weatherCondition

    private val _currentTemperature = MutableLiveData<String>()
    val currentTemperature: LiveData<String> get() = _currentTemperature

    private val _highLowTemperature = MutableLiveData<String>()
    val highLowTemperature: LiveData<String> get() = _highLowTemperature

    private val _weatherIcon = MutableLiveData<Int>()
    val weatherIcon: LiveData<Int> get() = _weatherIcon

    private val _hourlyWeather = MutableLiveData<List<HourlyWeather>>()
    val hourlyWeather: LiveData<List<HourlyWeather>> get() = _hourlyWeather

    private val _dailyWeather = MutableLiveData<List<DailyWeather>>()
    val dailyWeather: LiveData<List<DailyWeather>> get() = _dailyWeather

    // Put your real WeatherAPI.com key here
    private val apiKey = "put_your_api_key_here"

    fun updateDistrict(selectedCityName: String) {
        _cityName.value = selectedCityName
        fetchWeatherByCity(selectedCityName)
    }

    private fun fetchWeatherByCity(city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getForecastByCity(apiKey, city, days = 5)
                if (response.isSuccessful) {
                    response.body()?.let { forecast ->
                        parseWeatherForecast(forecast)
                    } ?: run {
                        setPlaceholderValues()
                    }
                } else {
                    setPlaceholderValues()
                }
            } catch (e: Exception) {
                setPlaceholderValues()
            }
        }
    }

    private fun parseWeatherForecast(forecast: WeatherAPIForecast) {
        // 1) Current
        val current = forecast.current
        val tempC = current?.tempC ?: 0.0
        val condText = current?.condition?.text ?: "N/A"

        _weatherCondition.value = condText
        _currentTemperature.value = "${tempC.toInt()}°"

        _weatherIcon.value = when {
            condText.contains("rain", ignoreCase = true) -> R.drawable.ic_weather_rainy
            condText.contains("cloud", ignoreCase = true) -> R.drawable.ic_weather_cloudy
            condText.contains("snow", ignoreCase = true)  -> R.drawable.ic_weather_snowy
            else -> R.drawable.ic_weather_sunny
        }

        // 2) Daily forecasts
        val forecastDayList = forecast.forecast?.forecastday ?: emptyList()
        if (forecastDayList.isNotEmpty()) {
            val dailyList = forecastDayList.map { fDay ->
                val dayCondText = fDay.day?.condition?.text ?: "N/A"
                val maxC = fDay.day?.maxTempC ?: 0.0
                val minC = fDay.day?.minTempC ?: 0.0

                val dayIcon = when {
                    dayCondText.contains("rain", ignoreCase = true) -> R.drawable.ic_weather_rainy
                    dayCondText.contains("cloud", ignoreCase = true) -> R.drawable.ic_weather_cloudy
                    dayCondText.contains("snow", ignoreCase = true)  -> R.drawable.ic_weather_snowy
                    else -> R.drawable.ic_weather_sunny
                }

                DailyWeather(
                    day = fDay.date ?: "",
                    highTemp = maxC.toInt(),
                    lowTemp = minC.toInt(),
                    iconResId = dayIcon
                )
            }
            _dailyWeather.value = dailyList

            val firstDay = dailyList[0]
            _highLowTemperature.value = "Highest:${firstDay.highTemp}° Lowest:${firstDay.lowTemp}°"
        } else {
            _dailyWeather.value = emptyList()
            _highLowTemperature.value = "Highest:0° Lowest:0°"
        }

        // 3) Hourly from “now” to next 24 hours
        // Gather all hours from the forecastDayList (for multiple days)
        val allHours = mutableListOf<Hour>()
        forecastDayList.forEach { dayItem ->
            dayItem.hour?.let { allHours.addAll(it) }
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val now = Date() // current local time

        // Filter out the hours that are in the past
        val futureHours = allHours.filter { hourItem ->
            val dateTimeStr = hourItem.time ?: "1970-01-01 00:00"
            val dateObj = sdf.parse(dateTimeStr)
            dateObj != null && dateObj.after(now)
        }

        // Take the next 24 entries from this list
        val next24Hours = futureHours.take(24)

        // Convert them to your HourlyWeather model
        val hourlyList = next24Hours.map { hourItem ->
            val hourCond = hourItem.condition?.text ?: "N/A"
            val hourCondIcon = when {
                hourCond.contains("rain", ignoreCase = true) -> R.drawable.ic_weather_rainy
                hourCond.contains("cloud", ignoreCase = true) -> R.drawable.ic_weather_cloudy
                hourCond.contains("snow", ignoreCase = true)  -> R.drawable.ic_weather_snowy
                else -> R.drawable.ic_weather_sunny
            }
            val hourTempC = hourItem.tempC ?: 0.0

            val dateTimeStr = hourItem.time ?: "1970-01-01 00:00"
            val timeStr = dateTimeStr.substringAfter(" ")

            HourlyWeather(
                time = timeStr,
                temperature = "${hourTempC.toInt()}°C",
                iconResId = hourCondIcon
            )
        }

        _hourlyWeather.value = hourlyList
    }

    private fun setPlaceholderValues() {
        _weatherCondition.value = "N/A"
        _currentTemperature.value = "0°"
        _highLowTemperature.value = "Highest:0° Lowest:0°"
        _weatherIcon.value = R.drawable.ic_weather_sunny
        _hourlyWeather.value = emptyList()
        _dailyWeather.value = emptyList()
    }
}