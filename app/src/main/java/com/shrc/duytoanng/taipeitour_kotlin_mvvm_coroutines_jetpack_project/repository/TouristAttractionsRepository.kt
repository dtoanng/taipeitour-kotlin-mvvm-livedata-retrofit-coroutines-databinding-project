package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.repository

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.AllTouristAttractions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.remote.TouristAttractionsApiService
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TouristAttractionsRepository @Inject constructor(private val apiService: TouristAttractionsApiService) : ITouristAttractionRepository {
    override suspend fun getAllTouristAttractions(language: String, page: Int): Flow<DataState<AllTouristAttractions>> {
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