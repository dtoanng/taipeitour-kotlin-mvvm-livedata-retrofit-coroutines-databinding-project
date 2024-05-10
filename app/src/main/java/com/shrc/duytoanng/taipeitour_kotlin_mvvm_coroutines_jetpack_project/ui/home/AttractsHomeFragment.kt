package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.AttractionsViewModel
import kotlinx.coroutines.launch

class AttractsHomeFragment : Fragment() {
    private val touristAttractions: AttractionsViewModel by viewModels()
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        touristAttractions.getTouristAttractions("vi")
        lifecycleScope.launch {
            touristAttractions.touristAttractions.collect { dataState ->
                Log.d("AttractionsActivity", "dataState: ${Gson().toJson(dataState)}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attractions_home, container, false)
    }

    companion object {

    }
}