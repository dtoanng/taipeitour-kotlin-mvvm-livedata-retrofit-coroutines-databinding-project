package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.di

import android.content.Context
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.local.PreferenceDataStoreHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): PreferenceDataStoreHelper {
        return PreferenceDataStoreHelper(context)
    }
}