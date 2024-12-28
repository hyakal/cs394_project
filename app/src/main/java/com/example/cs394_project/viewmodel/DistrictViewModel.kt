package com.example.cs394_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs394_project.model.District

class DistrictViewModel : ViewModel() {
    private val _districtList = MutableLiveData<List<District>>()
    val districtList: LiveData<List<District>> get() = _districtList

    init {
        _districtList.value = listOf(
            District("Adalar"), District("Arnavutköy"), District("Ataşehir"), District("Avcılar"),
            District("Bağcılar"), District("Bahçelievler"), District("Bakırköy"), District("Başakşehir"),
            District("Bayrampaşa"), District("Beşiktaş"), District("Beykoz"), District("Beylikdüzü"),
            District("Beyoğlu"), District("Büyükçekmece"), District("Çatalca"), District("Çekmeköy"),
            District("Esenler"), District("Esenyurt"), District("Eyüpsultan"), District("Fatih"),
            District("Gaziosmanpaşa"), District("Güngören"), District("Kadıköy"), District("Kağıthane"),
            District("Kartal"), District("Küçükçekmece"), District("Maltepe"), District("Pendik"),
            District("Sancaktepe"), District("Sarıyer"), District("Silivri"), District("Sultanbeyli"),
            District("Sultangazi"), District("Şile"), District("Şişli"), District("Tuzla"),
            District("Ümraniye"), District("Üsküdar"), District("Zeytinburnu")
        )
    }
}