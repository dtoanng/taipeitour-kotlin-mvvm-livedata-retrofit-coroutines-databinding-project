package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.utils

import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Language

class SupportedCountries {
    companion object {
        fun getSupportedCountries(): List<Language> = listOf(
            Language("Chinese", "zh-cn", R.drawable.cn),
            Language("Taiwanese", "zh-tw", R.drawable.tw),
            Language("English", "en", R.drawable.en),
            Language("Japanese", "ja", R.drawable.ja),
            Language("Korean", "ko", R.drawable.ko),
            Language("Spanish", "es", R.drawable.sp),
            Language("Indonesian", "id", R.drawable.id),
            Language("Thai", "th", R.drawable.th),
            Language("Vietnamese", "vi", R.drawable.vi),
        )

        fun getDefaultCountry() = getSupportedCountries()[2]
    }
}