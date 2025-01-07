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
import com.example.cs394_project.model.DailyWeather
import com.example.cs394_project.model.HourlyWeather
import com.example.cs394_project.viewmodel.WeatherViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    private val hourlyAdapter = HourlyWeatherAdapter(HourlyWeatherAdapter.HourlyWeatherDiffCallback())
    private val dailyAdapter = DailyWeatherAdapter(DailyWeatherAdapter.DailyWeatherDiffCallback())

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

        // Hourly RecyclerView setup
        binding.hourlyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hourlyRecyclerView.adapter = hourlyAdapter

        viewModel.hourlyWeather.observe(viewLifecycleOwner, Observer { hourlyList ->
            hourlyAdapter.submitList(hourlyList)
        })

        // Daily RecyclerView setup
        binding.dailyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.dailyRecyclerView.adapter = dailyAdapter

        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer { dailyList ->
            dailyAdapter.submitList(dailyList)
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
