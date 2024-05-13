package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionsHomeBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base.BaseFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.country.CountryDialogFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.launch
import timber.log.Timber

class AttractsHomeFragment : BaseFragment<FragmentAttractionsHomeBinding>() {

    private lateinit var backupLanguage: Language
    private var newLanguage: Language? = null
    private lateinit var attractionsAdapter: AttractionsAdapter

    override fun getViewBinding(): FragmentAttractionsHomeBinding =
        FragmentAttractionsHomeBinding.inflate(layoutInflater)

    override fun prepareData() {
        super.prepareData()
        backupLanguage = sharedViewModel.backupLanguage
        sharedViewModel.getTouristAttractions(backupLanguage.languageCode)
    }

    override fun observeData() {
        lifecycleScope.launch {
            sharedViewModel.touristAttractions.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        Timber.d("DataState.Loading")
                        binding.loadingData.visibility = View.VISIBLE
                    }

                    is DataState.Error -> {
                        Timber.d("DataState.Error: ${dataState.exception}")
                        binding.loadingData.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error:  ${dataState.exception.message}", Toast.LENGTH_SHORT).show()

                        //revert to previous flag if an error...
                        sharedViewModel.changeLanguage(sharedViewModel.backupLanguage)
                    }

                    else -> {
                        Timber.d("DataState.Success: ${Gson().toJson(dataState)}")
                        newLanguage?.let { sharedViewModel.backupLanguage = it }
                        drawAttractions((dataState as DataState.Success).data.touristAttraction as MutableList<Attraction>)
                    }
                }
            }
        }

        // observe changing language to update flag
        lifecycleScope.launch {
            sharedViewModel.currentLanguage.collect { country ->
                Timber.d("Selected country: $country")
                newLanguage = country
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