package com.example.cs394_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs394_project.R
import com.example.cs394_project.adapter.DailyWeatherAdapter
import com.example.cs394_project.adapter.HourlyWeatherAdapter
import com.example.cs394_project.databinding.FragmentMainBinding
import com.example.cs394_project.viewmodel.WeatherViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve city name from arguments
        val cityName = arguments?.getString("cityName") ?: "Unknown City"
        viewModel.updateDistrict(cityName) // or rename function to updateCity

        // Hourly Recycler
        binding.hourlyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.hourlyWeather.observe(viewLifecycleOwner, Observer { hourlyList ->
            binding.hourlyRecyclerView.adapter = HourlyWeatherAdapter(hourlyList)
        })

        // Daily Recycler
        binding.dailyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer { dailyList ->
            binding.dailyRecyclerView.adapter = DailyWeatherAdapter(dailyList)
        })
    }

    companion object {
        @BindingAdapter("app:srcCompat")
        @JvmStatic
        fun bindImageViewResource(imageView: ImageView, resource: Int?) {
            resource?.let { imageView.setImageResource(it) }
        }
    }
}