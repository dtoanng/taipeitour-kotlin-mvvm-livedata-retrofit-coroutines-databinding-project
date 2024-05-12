package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language
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
    private val _currentLanguage = MutableStateFlow(SupportedCountries.getDefaultCountry())
    val currentLanguage: StateFlow<Language> = _currentLanguage

    fun getTouristAttractions(lang: String) {
        viewModelScope.launch {
            repo.getAttractions(lang, 1).onEach {
                touristAttractions.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun changeLanguage(language: Language) {
        Timber.d("Selected language: $language")
        viewModelScope.launch {
            _currentLanguage.value = language
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentAttraction = null
    }
}