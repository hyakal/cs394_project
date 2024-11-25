package com.example.cs394_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs394_project.adapter.ItemAdapter
import com.example.cs394_project.databinding.FragmentMainBinding
import com.example.cs394_project.model.HourlyWeather

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data
        val weatherData = listOf(
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
            HourlyWeather("10:00", "-3°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("11:00", "-4°C", R.drawable.ic_weather_rainy),
            HourlyWeather("12:00", "7°C", R.drawable.ic_weather_sunny),
            HourlyWeather("13:00", "6°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("14:00", "5°C", R.drawable.ic_weather_rainy),
            HourlyWeather("15:00", "4°C", R.drawable.ic_weather_sunny),
            HourlyWeather("16:00", "3°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("17:00", "2°C", R.drawable.ic_weather_rainy),
            HourlyWeather("18:00", "1°C", R.drawable.ic_weather_sunny),
            HourlyWeather("19:00", "0°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("20:00", "-1°C", R.drawable.ic_weather_rainy),
            HourlyWeather("21:00", "-2°C", R.drawable.ic_weather_sunny),
            HourlyWeather("22:00", "-3°C", R.drawable.ic_weather_cloudy),
            HourlyWeather("23:00", "-4°C", R.drawable.ic_weather_rainy)
        )


        val adapter = ItemAdapter(weatherData)
        binding.hourlyRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hourlyRecyclerView.adapter = adapter

    }
}