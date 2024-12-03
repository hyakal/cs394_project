package com.example.cs394_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs394_project.R
import com.example.cs394_project.model.DailyWeather
import com.example.cs394_project.model.HourlyWeather

class WeatherViewModel : ViewModel() {

    private val _districtName = MutableLiveData<String>()
    val districtName: LiveData<String> get() = _districtName

    private val _weatherCondition = MutableLiveData<String>("Sunny")
    val weatherCondition: LiveData<String> get() = _weatherCondition

    private val _currentTemperature = MutableLiveData<String>("11°")
    val currentTemperature: LiveData<String> get() = _currentTemperature

    private val _highLowTemperature = MutableLiveData<String>("Highest:12° Lowest:7°")
    val highLowTemperature: LiveData<String> get() = _highLowTemperature

    private val _weatherIcon = MutableLiveData<Int>(R.drawable.ic_weather_sunny)
    val weatherIcon: LiveData<Int> get() = _weatherIcon

    private val _hourlyWeather = MutableLiveData<List<HourlyWeather>>()
    val hourlyWeather: LiveData<List<HourlyWeather>> get() = _hourlyWeather

    private val _dailyWeather = MutableLiveData<List<DailyWeather>>()
    val dailyWeather: LiveData<List<DailyWeather>> get() = _dailyWeather

    init {
        // Initialize with default weather data
        updateWeatherData("Unknown District")
    }

    fun updateDistrict(name: String) {
        _districtName.value = name
        updateWeatherData(name) // Update weather data dynamically
    }

    private fun updateWeatherData(district: String) {
        // Placeholder logic: Replace with actual API or database calls to fetch weather data
        when (district) {
            "Adalar" -> {
                _weatherCondition.value = "Cloudy"
                _currentTemperature.value = "15°"
                _highLowTemperature.value = "Highest:17° Lowest:12°"
                _weatherIcon.value = R.drawable.ic_weather_cloudy
                _hourlyWeather.value = listOf(
                    HourlyWeather("00:00", "15°C", R.drawable.ic_weather_cloudy),
                    HourlyWeather("01:00", "14°C", R.drawable.ic_weather_cloudy)
                    // Add more hourly weather data...
                )
                _dailyWeather.value = listOf(
                    DailyWeather("Monday", 12, 17, R.drawable.ic_weather_cloudy),
                    DailyWeather("Tuesday", 14, 18, R.drawable.ic_weather_sunny)
                    // Add more daily weather data...
                )
            }
            "Ataşehir" -> {
                _weatherCondition.value = "Sunny"
                _currentTemperature.value = "20°"
                _highLowTemperature.value = "Highest:22° Lowest:18°"
                _weatherIcon.value = R.drawable.ic_weather_sunny
                _hourlyWeather.value = listOf(
                    HourlyWeather("00:00", "20°C", R.drawable.ic_weather_sunny),
                    HourlyWeather("01:00", "19°C", R.drawable.ic_weather_sunny)
                    // Add more hourly weather data...
                )
                _dailyWeather.value = listOf(
                    DailyWeather("Monday", 18, 22, R.drawable.ic_weather_sunny),
                    DailyWeather("Tuesday", 19, 23, R.drawable.ic_weather_cloudy)
                    // Add more daily weather data...
                )
            }
            else -> {
                _weatherCondition.value = "Sunny"
                _currentTemperature.value = "11°"
                _highLowTemperature.value = "Highest:12° Lowest:7°"
                _weatherIcon.value = R.drawable.ic_weather_sunny
                _hourlyWeather.value = listOf(
                    HourlyWeather("00:00", "11°C", R.drawable.ic_weather_sunny),
                    HourlyWeather("01:00", "10°C", R.drawable.ic_weather_cloudy)
                    // Add more hourly weather data...
                )
                _dailyWeather.value = listOf(
                    DailyWeather("Monday", 7, 12, R.drawable.ic_weather_cloudy),
                    DailyWeather("Tuesday", 6, 14, R.drawable.ic_weather_sunny)
                    // Add more daily weather data...
                )
            }
        }
    }
}
