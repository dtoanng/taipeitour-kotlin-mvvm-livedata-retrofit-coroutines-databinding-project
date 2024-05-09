package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model

import com.google.gson.annotations.SerializedName

data class TouristAttraction(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_zh")
    val nameZh: Any,
    @SerializedName("open_status")
    val openStatus: Int,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("open_time")
    val openTime: String,
    @SerializedName("zipcode")
    val zipcode: String,
    @SerializedName("distric")
    val district: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("tel")
    val telephoneNumber: String,
    @SerializedName("fax")
    val fax: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("months")
    val months: String,
    @SerializedName("nlat")
    val nLat: Double,
    @SerializedName("elong")
    val eLong: Double,
    @SerializedName("official_site")
    val officialSite: String,
    @SerializedName("facebook")
    val facebook: String,
    @SerializedName("ticket")
    val ticket: String,
    @SerializedName("remind")
    val remind: String,
    @SerializedName("staytime")
    val stayTime: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("target")
    val target: List<Target>,
    @SerializedName("service")
    val service: List<Service>,
    @SerializedName("friendly")
    val friendly: List<Any>,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("files")
    val files: List<Any>,
    @SerializedName("links")
    val links: List<Link>
)