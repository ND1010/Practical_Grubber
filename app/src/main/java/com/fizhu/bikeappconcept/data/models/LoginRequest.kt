package com.fizhu.bikeappconcept.data.models


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("AppVersion")
    var appVersion: String = "",
    @SerializedName("DeviceID")
    var deviceID: String = "",
    @SerializedName("DeviceType")
    var deviceType: String = "",
    @SerializedName("FCMID")
    var fCMID: String = "",
    @SerializedName("ModuleID")
    var moduleID: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("Username")
    var username: String = ""
)