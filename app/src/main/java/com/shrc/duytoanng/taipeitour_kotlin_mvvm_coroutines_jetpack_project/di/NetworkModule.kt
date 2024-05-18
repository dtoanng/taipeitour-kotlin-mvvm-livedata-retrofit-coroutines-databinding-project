package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.di

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.ApiConstants
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.AttractionsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = ApiConstants.BASE_URL


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().also {
                        it.addHeader("accept", "application/json")
                    }.build()
                )
            }
            .callTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()


    @Singleton
    @Provides
    fun provideRestApiService(retrofit: Retrofit): AttractionsApiService =
        retrofit.create(AttractionsApiService::class.java)

}