package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attractions
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AttractionsApiService {
    @GET("{lang}/Attractions/All")
    suspend fun getAllTouristAttractions(
        @Path("lang") lang: String,
        @Query("page") page: String
    ): Attractions

    // another APIs
}