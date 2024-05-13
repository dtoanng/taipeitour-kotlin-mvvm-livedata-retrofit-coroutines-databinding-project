package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.local.PreferenceDataStoreConstants
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.local.PreferenceDataStoreHelper
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository.TouristAttractionsRepository
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.SupportedCountries
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repo: TouristAttractionsRepository, private val dataStore: PreferenceDataStoreHelper) :
    ViewModel() {

    var currentAttraction: Attraction? = null
    var backupLanguage: Language = SupportedCountries.getDefaultCountry()

    val touristAttractions: MutableStateFlow<DataState<Attractions>> = MutableStateFlow(DataState.Loading)

    private val _currentLanguage = MutableStateFlow(backupLanguage)
    val currentLanguage: StateFlow<Language> = _currentLanguage

    fun getAvailableLanguage() {
        viewModelScope.launch {
            dataStore.getPreference(PreferenceDataStoreConstants.COUNTRY_KEY, SupportedCountries.getDefaultCountry().languageCode).first {

                // set backup language from local
                backupLanguage = SupportedCountries.getSupportedCountries().first { lang ->
                    lang.languageCode == it
                }

                // get attractions after there is a user's selections
                getTouristAttractions(it)

                // change language flag to saved language before
                changeLanguage(backupLanguage)
                true
            }
        }
    }

    fun setDefaultLanguage() {
        viewModelScope.launch {
            dataStore.putPreference(PreferenceDataStoreConstants.COUNTRY_KEY, backupLanguage.languageCode)
        }
    }

    fun getTouristAttractions(lang: String) {
        viewModelScope.launch {
            repo.getAttractions(lang, 1).onEach {
                touristAttractions.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun changeLanguage(language: Language) {
        viewModelScope.launch {
            _currentLanguage.value = language
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentAttraction = null
    }
}