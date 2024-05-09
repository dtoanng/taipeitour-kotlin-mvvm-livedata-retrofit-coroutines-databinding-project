package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.networkconnection

sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}