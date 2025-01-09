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
import android.text.TextWatcher
import android.text.Editable


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


        viewModel.cityList.observe(viewLifecycleOwner, Observer { cities ->
            val adapter = CityAdapter(cities) { city: City ->

                val action = CityFragmentDirections.actionCityFragmentToMainFragment(city.name)
                findNavController().navigate(action)
            }
            binding.recyclerViewCities.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewCities.adapter = adapter
        })
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                viewModel.filterCities(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}

