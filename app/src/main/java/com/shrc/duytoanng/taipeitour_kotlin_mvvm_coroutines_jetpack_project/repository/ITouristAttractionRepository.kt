package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.repository

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.AllTouristAttractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface ITouristAttractionRepository {
    suspend fun getAllTouristAttractions(language: String, page: Int): Flow<DataState<AllTouristAttractions>>
    // another functions
}