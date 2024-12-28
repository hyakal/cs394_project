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
import com.example.cs394_project.adapter.DistrictAdapter
import com.example.cs394_project.databinding.FragmentDistrictBinding
import com.example.cs394_project.model.District
import com.example.cs394_project.viewmodel.DistrictViewModel

class DistrictFragment : Fragment() {

    private lateinit var binding: FragmentDistrictBinding
    private val viewModel: DistrictViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_district, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the district list from ViewModel
        viewModel.districtList.observe(viewLifecycleOwner, Observer { districts ->
            val adapter = DistrictAdapter(districts) { district: District ->
                val action = DistrictFragmentDirections.actionDistrictFragmentToMainFragment(district.name)
                findNavController().navigate(action)
            }

            binding.recyclerViewDistricts.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewDistricts.adapter = adapter
        })
    }
}