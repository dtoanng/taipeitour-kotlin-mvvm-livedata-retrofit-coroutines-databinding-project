package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentCountrySelectionBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.AttractionsViewModel
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils.SupportedCountries

class CountryDialogFragment : DialogFragment() {
    val sharedViewModel: AttractionsViewModel by activityViewModels()
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var binding: FragmentCountrySelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountrySelectionBinding.inflate(inflater, container, false)
        dialog?.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val anotherCountry = getUnselectedCountries()
        drawCountryList(anotherCountry)
    }

    private fun drawCountryList(countries: List<Language>) {
        countryAdapter = CountryAdapter(countries).apply {
            setOnClickListener(object : CountryAdapter.OnItemClickLister {
                override fun onItemClick(language: Language) {
                    Toast.makeText(requireContext(), language.name, Toast.LENGTH_SHORT).show()
                    dialog?.dismiss()
                    sharedViewModel.apply {
                        changeLanguage(language)
                        getTouristAttractions(language.languageCode, 1)
                    }
                }
            })
        }
        binding.rvCountries.adapter = countryAdapter
    }

    private fun getUnselectedCountries(): MutableList<Language> {
        val selectedCountry = sharedViewModel.currentLanguage.value
        val supportedCountry = SupportedCountries.getSupportedCountries().toMutableList()

        supportedCountry.removeIf {
            it.languageCode == selectedCountry.languageCode
        }

        return supportedCountry
    }
}