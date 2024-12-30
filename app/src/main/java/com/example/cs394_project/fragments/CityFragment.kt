package com.example.cs394_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs394_project.R
import com.example.cs394_project.adapter.CityAdapter
import com.example.cs394_project.databinding.FragmentCityBinding
import com.example.cs394_project.model.City
import com.example.cs394_project.viewmodel.CityViewModel

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding
    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the city list from ViewModel
        viewModel.cityList.observe(viewLifecycleOwner, Observer { cities ->
            val adapter = CityAdapter(cities) { city: City ->
                // Navigate to MainFragment, passing the city name
                val action = CityFragmentDirections.actionCityFragmentToMainFragment(city.name)
                findNavController().navigate(action)
            }
            binding.recyclerViewCities.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewCities.adapter = adapter
        })
    }
}