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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve district name from arguments and pass it to ViewModel
        val districtName = arguments?.getString("districtName") ?: "Unknown District"
        viewModel.updateDistrict(districtName)

        // Set up hourly weather RecyclerView
        binding.hourlyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.hourlyWeather.observe(viewLifecycleOwner, Observer { hourlyWeather ->
            binding.hourlyRecyclerView.adapter = HourlyWeatherAdapter(hourlyWeather)
        })

        // Set up daily weather RecyclerView
        binding.dailyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer { dailyWeather ->
            binding.dailyRecyclerView.adapter = DailyWeatherAdapter(dailyWeather)
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
