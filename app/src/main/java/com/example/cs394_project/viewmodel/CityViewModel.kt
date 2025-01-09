package com.example.cs394_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs394_project.model.City

class CityViewModel : ViewModel() {
    private val _cityList = MutableLiveData<List<City>>()
    val cityList = MutableLiveData<List<City>>()

    init {
        _cityList.value = listOf(
            City("Adana"),
            City("Adıyaman"),
            City("Afyonkarahisar"),
            City("Ağrı"),
            City("Aksaray"),
            City("Amasya"),
            City("Ankara"),
            City("Antalya"),
            City("Ardahan"),
            City("Artvin"),
            City("Aydın"),
            City("Balıkesir"),
            City("Bartın"),
            City("Batman"),
            City("Bayburt"),
            City("Bilecik"),
            City("Bingöl"),
            City("Bitlis"),
            City("Bolu"),
            City("Burdur"),
            City("Bursa"),
            City("Çanakkale"),
            City("Çankırı"),
            City("Çorum"),
            City("Denizli"),
            City("Diyarbakır"),
            City("Düzce"),
            City("Edirne"),
            City("Elazığ"),
            City("Erzincan"),
            City("Erzurum"),
            City("Eskişehir"),
            City("Gaziantep"),
            City("Giresun"),
            City("Gümüşhane"),
            City("Hakkâri"),
            City("Hatay"),
            City("Iğdır"),
            City("Isparta"),
            City("İstanbul"),
            City("İzmir"),
            City("Kahramanmaraş"),
            City("Karabük"),
            City("Karaman"),
            City("Kars"),
            City("Kastamonu"),
            City("Kayseri"),
            City("Kırıkkale"),
            City("Kırklareli"),
            City("Kırşehir"),
            City("Kilis"),
            City("Kocaeli"),
            City("Konya"),
            City("Kütahya"),
            City("Malatya"),
            City("Manisa"),
            City("Mardin"),
            City("Mersin"),
            City("Muğla"),
            City("Muş"),
            City("Nevşehir"),
            City("Niğde"),
            City("Ordu"),
            City("Osmaniye"),
            City("Rize"),
            City("Sakarya"),
            City("Samsun"),
            City("Siirt"),
            City("Sinop"),
            City("Sivas"),
            City("Şanlıurfa"),
            City("Şırnak"),
            City("Tekirdağ"),
            City("Tokat"),
            City("Trabzon"),
            City("Tunceli"),
            City("Uşak"),
            City("Van"),
            City("Yalova"),
            City("Yozgat"),
            City("Zonguldak")
        )
        cityList.value = _cityList.value
    }
    fun filterCities(query: String) {
        if (query.isEmpty()) {
            // Eğer arama boşsa, tüm listeyi göster
            cityList.value = _cityList.value
        } else {
            // Arama sorgusuna göre filtrele
            cityList.value = _cityList.value?.filter { city ->
                city.name.lowercase().startsWith(query.lowercase())
            }
        }
    }
}