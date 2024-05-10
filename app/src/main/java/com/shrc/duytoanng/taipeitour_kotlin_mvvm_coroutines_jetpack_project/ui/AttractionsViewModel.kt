package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository.TouristAttractionsRepository
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repo: TouristAttractionsRepository) : ViewModel() {

    var page = 1
    val touristAttractions: MutableStateFlow<DataState<Attractions>> = MutableStateFlow(DataState.Loading)

    fun getTouristAttractions(lang: String) {
        viewModelScope.launch {
            repo.getAttractions(lang, page).onEach {
                touristAttractions.value = it
            }.launchIn(viewModelScope)
        }
        page++
    }

    override fun onCleared() {
        super.onCleared()
        page = 1
    }
}