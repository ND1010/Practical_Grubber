package com.fizhu.bikeappconcept.utils.base


import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("text")
    var message: String = "",
    @SerializedName("statusCode")
    var statusCode: Int = 0
)