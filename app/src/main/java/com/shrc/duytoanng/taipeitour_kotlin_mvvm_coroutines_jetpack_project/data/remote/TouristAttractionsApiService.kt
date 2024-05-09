package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.remote

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.AllTouristAttractions
import retrofit2.http.GET
import retrofit2.http.Query

interface TouristAttractionsApiService {
    @GET("{lang}/Attractions/All?page={page}")
    suspend fun getAllTouristAttractions(
        @Query("lang") lang: String,
        @Query("page") page: String
    ): AllTouristAttractions

    // another APIs
}