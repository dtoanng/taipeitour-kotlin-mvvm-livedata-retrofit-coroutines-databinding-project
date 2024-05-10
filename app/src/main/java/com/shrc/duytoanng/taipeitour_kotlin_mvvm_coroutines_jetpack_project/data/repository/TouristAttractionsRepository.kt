package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.AttractionsApiService
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TouristAttractionsRepository @Inject constructor(private val apiService: AttractionsApiService) : AttractionRepositoryInterface {
    override suspend fun getAttractions(language: String, page: Int): Flow<DataState<Attractions>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.getAllTouristAttractions(language, page.toString())
                emit(DataState.Success(response))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }
}