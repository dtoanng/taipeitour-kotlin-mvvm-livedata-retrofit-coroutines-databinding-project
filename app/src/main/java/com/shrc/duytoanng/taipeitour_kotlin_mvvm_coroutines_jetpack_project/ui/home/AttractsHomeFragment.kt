package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionsHomeBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.AttractionsViewModel
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.launch

class AttractsHomeFragment : Fragment() {
    private val touristAttractions: AttractionsViewModel by activityViewModels()
    private lateinit var mBinding: FragmentAttractionsHomeBinding
    private lateinit var attractionsAdapter: AttractionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        touristAttractions.getTouristAttractions("vi")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAttractionsHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            touristAttractions.touristAttractions.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        Log.d("AttractionsActivity", "DataState.Loading")
                    }

                    is DataState.Error -> {
                        Log.d("AttractionsActivity", "DataState.Error: ${Gson().toJson(dataState)}")
                    }

                    else -> {
                        Log.d(
                            "AttractionsActivity",
                            "DataState.Success: ${Gson().toJson(dataState)}"
                        )
                        attractionsAdapter = AttractionsAdapter(
                            attractions = (dataState as DataState.Success).data.touristAttraction as MutableList<Attraction>
                        )
                        mBinding.rvAttractions.adapter = attractionsAdapter
                        mBinding.loadingData.visibility = View.GONE
                        attractionsAdapter.setOnClickListener(object :
                            AttractionsAdapter.OnItemClickLister {
                            override fun onItemClick(attraction: Attraction) {
                                findNavController().navigate(R.id.attractionDetailFragment)
                            }
                        })
                    }
                }
            }
        }
    }
}