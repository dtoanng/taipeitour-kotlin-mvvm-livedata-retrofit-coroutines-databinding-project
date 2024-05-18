package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionsHomeBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base.BaseFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.country.CountryDialogFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.QUERY_PAGE_SIZE
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.network.DataState
import kotlinx.coroutines.launch
import timber.log.Timber

class AttractsHomeFragment : BaseFragment<FragmentAttractionsHomeBinding>() {

    private var newLanguage: Language? = null
    private lateinit var attractionsAdapter: AttractionsAdapter
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun getViewBinding(): FragmentAttractionsHomeBinding =
        FragmentAttractionsHomeBinding.inflate(layoutInflater)

    override fun setupView() {
        super.setupView()
        attractionsAdapter = AttractionsAdapter(sharedViewModel.attractions)
        binding.rvAttractions.adapter = attractionsAdapter
    }

    override fun setupListener() {
        super.setupListener()

        attractionsAdapter.setOnClickListener(
            object : AttractionsAdapter.OnItemClickLister {
                override fun onItemClick(attraction: Attraction) {
                    sharedViewModel.currentAttraction = attraction
                    findNavController().navigate(R.id.attractionDetailFragment)
                }
            },
        )

        with(binding) {
            attractionTitleArea.ivSelectedCountry.setOnClickListener {
                CountryDialogFragment().show(childFragmentManager, "CountryDialogFragment")
            }

            rvAttractions.addOnScrollListener(this@AttractsHomeFragment.scrollListener)
        }
    }

    override fun prepareData() {
        super.prepareData()
        sharedViewModel.apply {
            sharedViewModel.fetchData()
            newLanguage = this.backupLanguage
        }
    }

    override fun observeData() {
        super.observeData()
        // observe data from Api
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
                        Toast.makeText(
                            requireContext(),
                            "Error:  ${dataState.exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                        //revert to previous flag if an error...
                        sharedViewModel.changeLanguage(sharedViewModel.backupLanguage)
                    }

                    else -> {
                        binding.loadingData.visibility = View.GONE
                        Timber.d("DataState.Success:\npage: ${sharedViewModel.attractionsPage}\nData: ${Gson().toJson(dataState)}")
                        with(sharedViewModel) {
                            if (this.backupLanguage != newLanguage) {
                                this.backupLanguage = newLanguage!!
                                this.attractionsPage = 1
                                attractionsAdapter.clearData()
                                setDefaultLanguage()
                            } else {
                                this.attractionsPage++
                            }
                        }

                        // add new data to adapter
                        val newAttractions = (dataState as DataState.Success).data.touristAttraction as MutableList<Attraction>
                        attractionsAdapter.loadMoreData(newAttractions)
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
                    ContextCompat.getDrawable(requireContext(), country.src)
                )
            }
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val totalVisibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + totalVisibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                sharedViewModel.getTouristAttractions(sharedViewModel.backupLanguage.languageCode)
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) { //State is scrolling
                isScrolling = true
            }
        }
    }
}