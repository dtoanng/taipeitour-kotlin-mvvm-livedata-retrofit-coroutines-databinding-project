package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import androidx.lifecycle.ViewModel
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.repository.TouristAttractionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VmTouristAttractions @Inject constructor(private val repo: TouristAttractionsRepository): ViewModel() {

}