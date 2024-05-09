package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("src")
    val src: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("ext")
    val ext: String
)