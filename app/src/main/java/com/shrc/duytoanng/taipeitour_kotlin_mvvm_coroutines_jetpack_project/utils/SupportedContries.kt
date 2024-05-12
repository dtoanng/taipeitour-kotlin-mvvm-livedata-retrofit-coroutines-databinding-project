package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Country

class SupportedCountries {
    companion object {
        fun getSupportedCountries(): List<Country> = listOf(
            Country("China", "zh-cn", R.drawable.cn),
            Country("Taiwan", "zh-tw", R.drawable.tw),
            Country("United States", "en", R.drawable.en),
            Country("Japan", "ja", R.drawable.ja),
            Country("Korea", "ko", R.drawable.ko),
            Country("Spain", "es", R.drawable.sp),
            Country("Indonesia", "in", R.drawable.id),
            Country("Thailand", "th", R.drawable.th),
            Country("Vietnam", "vi", R.drawable.vi),
        )

        fun getDefaultCountry() = getSupportedCountries()[2]
    }
}