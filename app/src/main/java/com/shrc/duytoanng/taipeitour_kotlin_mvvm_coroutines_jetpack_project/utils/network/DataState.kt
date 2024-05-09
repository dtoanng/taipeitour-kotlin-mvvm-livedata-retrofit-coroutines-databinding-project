package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}