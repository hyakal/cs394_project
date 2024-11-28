package com.example.cs394_project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs394_project.R
import com.example.cs394_project.adapter.DailyWeatherAdapter
import com.example.cs394_project.adapter.HourlyWeatherAdapter
import com.example.cs394_project.databinding.FragmentMainBinding
import com.example.cs394_project.model.DailyWeather
import com.example.cs394_project.model.HourlyWeather

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        // Retrieve district name from arguments
        val districtName = arguments?.getString("districtName") ?: "Unknown District"
        binding.districtTextView.text = districtName

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set current weather data
        val districtName = arguments?.getString("districtName") ?: "Unknown District"
        val weatherCondition = "Sunny" // Change dynamically in the future
        binding.districtTextView.text = districtName
        binding.weatherDescription.text = weatherCondition
        binding.temperatureTextView.text = "11°"
        binding.highLowTextView.text = "Highest:12° Lowest:7°"

        // Set the weather icon dynamically
        val weatherIconResId = when (weatherCondition) {
            "Sunny" -> R.drawable.ic_weather_sunny
            "Rainy" -> R.drawable.ic_weather_rainy
            "Cloudy" -> R.drawable.ic_weather_cloudy
            else -> R.drawable.ic_weather_foggy
        }
        binding.weatherIcon.setImageResource(weatherIconResId)

        // Set hourly weather data
        val hourlyWeather = listOf(
            HourlyWeather("00:00", "7°C", R.drawable.ic_weather_sunny),
            HourlyWeather("01:00", "6°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("02:00", "5°C", R.drawable.ic_weather_rainy),
            HourlyWeather("03:00", "4°C", R.drawable.ic_weather_sunny),
            HourlyWeather("04:00", "3°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("05:00", "2°C", R.drawable.ic_weather_rainy),
            HourlyWeather("06:00", "1°C", R.drawable.ic_weather_sunny),
            HourlyWeather("07:00", "0°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("08:00", "-1°C", R.drawable.ic_weather_rainy),
            HourlyWeather("09:00", "-2°C", R.drawable.ic_weather_sunny),
            HourlyWeather("10:00", "0°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("11:00", "2°C", R.drawable.ic_weather_sunny),
            HourlyWeather("12:00", "5°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("13:00", "6°C", R.drawable.ic_weather_sunny),
            HourlyWeather("14:00", "7°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("15:00", "8°C", R.drawable.ic_weather_sunny),
            HourlyWeather("16:00", "7°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("17:00", "6°C", R.drawable.ic_weather_rainy),
            HourlyWeather("18:00", "5°C", R.drawable.ic_weather_sunny),
            HourlyWeather("19:00", "4°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("20:00", "3°C", R.drawable.ic_weather_rainy),
            HourlyWeather("21:00", "2°C", R.drawable.ic_weather_sunny),
            HourlyWeather("22:00", "1°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("23:00", "0°C", R.drawable.ic_weather_rainy)
        )

        binding.hourlyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hourlyRecyclerView.adapter = HourlyWeatherAdapter(hourlyWeather)

        // Set daily weather data
        val dailyWeather = listOf(
            DailyWeather("Monday", 7, 12, R.drawable.ic_weather_cloudy),
            DailyWeather("Tuesday", 6, 14, R.drawable.ic_weather_sunny),
            DailyWeather("Wednesday", 8, 15, R.drawable.ic_weather_rainy),
            DailyWeather("Thursday", 9, 16, R.drawable.ic_weather_cloudy),
            DailyWeather("Friday", 5, 13, R.drawable.ic_weather_sunny),
            DailyWeather("Saturday", 6, 14, R.drawable.ic_weather_cloudy),
            DailyWeather("Sunday", 7, 12, R.drawable.ic_weather_rainy)
        )

        binding.dailyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.dailyRecyclerView.adapter = DailyWeatherAdapter(dailyWeather)
    }
}
