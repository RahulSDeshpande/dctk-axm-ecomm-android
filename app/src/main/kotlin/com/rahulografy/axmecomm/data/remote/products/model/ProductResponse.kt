package com.rahulografy.axmecomm.data.remote.products.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("announceDate") val announceDate: Int?,
    @SerializedName("audioJack") val audioJack: String?,
    @SerializedName("battery") val battery: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("gps") val gps: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("picture") val picture: String?,
    @SerializedName("priceEur") val priceEur: Int?,
    @SerializedName("resolution") val resolution: String?,
    @SerializedName("sim") val sim: String?
)