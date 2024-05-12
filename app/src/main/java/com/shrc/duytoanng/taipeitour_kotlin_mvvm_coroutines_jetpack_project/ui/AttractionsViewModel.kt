package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Country
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository.TouristAttractionsRepository
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.SupportedCountries
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repo: TouristAttractionsRepository) :
    ViewModel() {

    var currentAttraction: Attraction? = null

    val touristAttractions: MutableStateFlow<DataState<Attractions>> = MutableStateFlow(DataState.Loading)

    private val _currentCountry = MutableStateFlow(SupportedCountries.getDefaultCountry())
    val currentCountry: StateFlow<Country> = _currentCountry

    fun getTouristAttractions(lang: String) {
        viewModelScope.launch {
            repo.getAttractions(lang, 1).onEach {
                touristAttractions.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun changeLanguage(country: Country) {
        Timber.d("dtoanng: $country")
        viewModelScope.launch {
            _currentCountry.value = country
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentAttraction = null
    }
}