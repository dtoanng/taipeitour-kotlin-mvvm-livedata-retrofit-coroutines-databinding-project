package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import android.content.Context
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
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.networkconnection.ConnectionState
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.networkconnection.observeConnectivityAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(
    private val repo: TouristAttractionsRepository,
    private val dataStore: PreferenceDataStoreHelper
) :
    ViewModel() {

    var currentAttraction: Attraction? = null
    var backupLanguage: Language = SupportedCountries.getDefaultCountry()
    val attractions by lazy { mutableListOf<Attraction>() }
    var attractionsPage = 1

    val touristAttractions: MutableStateFlow<DataState<Attractions>> =
        MutableStateFlow(DataState.Loading)

    private val _currentLanguage = MutableStateFlow(backupLanguage)
    val currentLanguage: StateFlow<Language> = _currentLanguage

    private val _networkState = MutableStateFlow(true)
    val networkState: StateFlow<Boolean> = _networkState

    fun checkInternetConnection(context: Context) {
        viewModelScope.launch {
            context.observeConnectivityAsFlow().distinctUntilChanged().collect {

                _networkState.value = it == ConnectionState.Available

                delay(500)
                if (it == ConnectionState.Available && attractions.isEmpty()) {
                    getTouristAttractions(backupLanguage.languageCode)
                }
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            dataStore.getPreference(
                PreferenceDataStoreConstants.COUNTRY_KEY,
                SupportedCountries.getDefaultCountry().languageCode
            ).first {

                // set backup language from local
                backupLanguage = SupportedCountries.getSupportedCountries().first { lang ->
                    lang.languageCode == it
                }

                // get attractions after there is a user's selections
                getTouristAttractions(backupLanguage.languageCode)

                // change language flag to saved language before
                setCurrentLanguage(backupLanguage)
                true
            }
        }
    }

    fun setDefaultLanguage() {
        viewModelScope.launch {
            dataStore.putPreference(
                PreferenceDataStoreConstants.COUNTRY_KEY,
                backupLanguage.languageCode
            )
        }
    }

    fun getTouristAttractions(lang: String, page: Int = attractionsPage) {
        viewModelScope.launch {
            repo.getAttractions(lang, page).onEach {
                touristAttractions.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun changeLanguage(language: Language) {
        viewModelScope.launch {
            setCurrentLanguage(language)
        }
    }

    private fun setCurrentLanguage(language: Language) {
        _currentLanguage.value = language
    }

    override fun onCleared() {
        super.onCleared()
        currentAttraction = null
    }
}