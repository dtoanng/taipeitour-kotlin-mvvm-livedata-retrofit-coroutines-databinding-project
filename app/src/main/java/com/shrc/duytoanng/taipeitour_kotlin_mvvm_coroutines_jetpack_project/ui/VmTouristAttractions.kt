package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.ViewModel
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.AllTouristAttractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.repository.TouristAttractionsRepository
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class VmTouristAttractions @Inject constructor(private val repo: TouristAttractionsRepository) : ViewModel() {

    val touristAttractions: MutableStateFlow<DataState<AllTouristAttractions>> = MutableStateFlow(DataState.Loading)
}