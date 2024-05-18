package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.di

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.AttractionsApiService
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.repository.TouristAttractionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTouristAttractionsRepository(
        apiService: AttractionsApiService,
    ): TouristAttractionsRepository = TouristAttractionsRepository(apiService)

}