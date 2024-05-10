package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository

import androidx.paging.PagingData
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface AttractionRepositoryInterface {
    suspend fun getAttractions(language: String, page: Int): Flow<DataState<Attractions>>
}