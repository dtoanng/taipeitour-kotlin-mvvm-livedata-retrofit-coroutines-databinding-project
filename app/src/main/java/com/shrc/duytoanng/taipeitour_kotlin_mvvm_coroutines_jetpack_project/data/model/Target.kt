package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model

import com.google.gson.annotations.SerializedName

data class Target(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)