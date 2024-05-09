package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model

import com.google.gson.annotations.SerializedName

data class AllTouristAttractions(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val touristAttraction: List<TouristAttraction>
)
