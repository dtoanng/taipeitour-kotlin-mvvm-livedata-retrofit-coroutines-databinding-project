package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionsHomeBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base.BaseFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.country.CountryDialogFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.launch
import timber.log.Timber

class AttractsHomeFragment : BaseFragment<FragmentAttractionsHomeBinding>() {

    private lateinit var attractionsAdapter: AttractionsAdapter

    override fun getViewBinding(): FragmentAttractionsHomeBinding =
        FragmentAttractionsHomeBinding.inflate(layoutInflater)

    override fun prepareData() {
        super.prepareData()
        sharedViewModel.getTouristAttractions("en")
    }

    override fun observeData() {
        lifecycleScope.launch {
            sharedViewModel.touristAttractions.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        Timber.d("DataState.Loading")
                    }

                    is DataState.Error -> {
                        Timber.d("DataState.Error: ${dataState.exception}")
                    }

                    else -> {
                        Timber.d("DataState.Success: ${Gson().toJson(dataState)}")
                        drawAttractions((dataState as DataState.Success).data.touristAttraction as MutableList<Attraction>)
                    }
                }
            }
        }

        lifecycleScope.launch {
            sharedViewModel.currentCountry.collect { country ->
                Timber.d("Selected country: $country")
                binding.attractionTitleArea.ivSelectedCountry.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        country.src
                    )
                )
            }
        }
    }

    private fun drawAttractions(attractions: MutableList<Attraction>) {
        // create adapter
        attractionsAdapter = AttractionsAdapter(attractions).apply {
            setOnClickListener(object : AttractionsAdapter.OnItemClickLister {
                override fun onItemClick(attraction: Attraction) {
                    sharedViewModel.currentAttraction = attraction
                    findNavController().navigate(R.id.attractionDetailFragment)
                }
            })
        }

        // set adapter
        with(binding) {
            rvAttractions.adapter = attractionsAdapter
            loadingData.visibility = View.GONE
        }
    }

    override fun setupListener() {
        super.setupListener()
        binding.attractionTitleArea.ivSelectedCountry.setOnClickListener {
            CountryDialogFragment().show(childFragmentManager, "CountryDialogFragment")
        }
    }
}