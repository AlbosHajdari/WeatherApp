package com.example.weatherapp.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class WetBulbTemperature {
    @SerializedName("Value")
    @Expose
    val value: Double? = null

    @SerializedName("Unit")
    @Expose
    val unit: String? = null

    @SerializedName("UnitType")
    @Expose
    val unitType: Int? = null

}