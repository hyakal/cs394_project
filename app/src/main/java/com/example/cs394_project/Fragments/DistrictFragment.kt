package com.example.cs394_project.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs394_project.R
import com.example.cs394_project.adapter.DistrictAdapter
import com.example.cs394_project.databinding.FragmentDistrictBinding
import com.example.cs394_project.model.District

class DistrictFragment : Fragment() {

    private lateinit var binding: FragmentDistrictBinding

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

        // Sample district data, replace this with actual data if needed
        val districtList = listOf(
            "Adalar", "Arnavutköy", "Ataşehir", "Avcılar", "Bağcılar", "Bahçelievler",
            "Bakırköy", "Başakşehir", "Bayrampaşa", "Beşiktaş", "Beykoz", "Beylikdüzü",
            "Beyoğlu", "Büyükçekmece", "Çatalca", "Çekmeköy", "Esenler", "Esenyurt",
            "Eyüpsultan", "Fatih", "Gaziosmanpaşa", "Güngören", "Kadıköy", "Kağıthane",
            "Kartal", "Küçükçekmece", "Maltepe", "Pendik", "Sancaktepe", "Sarıyer", "Silivri",
            "Sultanbeyli", "Sultangazi", "Şile", "Şişli", "Tuzla", "Ümraniye", "Üsküdar", "Zeytinburnu"
        )
        val districts = districtList.map { District(it) }

        // Set up RecyclerView with the DistrictAdapter
        val adapter = DistrictAdapter(districts) { district ->
            val action = DistrictFragmentDirections.actionDistrictFragmentToMainFragment(district.name)
            findNavController().navigate(action)
        }


        binding.recyclerViewDistricts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDistricts.adapter = adapter
    }
}
