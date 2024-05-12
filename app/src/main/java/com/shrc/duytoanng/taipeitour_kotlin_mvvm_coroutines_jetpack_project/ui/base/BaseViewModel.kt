package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base

import androidx.lifecycle.ViewModel

/**todo: implement base viewmodel when we dont want to use shared view model */
class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}