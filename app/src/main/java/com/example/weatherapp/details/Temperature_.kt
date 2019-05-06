package com.example.weatherapp.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Temperature_ {
    @SerializedName("Value")
    @Expose
    var value: Double? = null

    @SerializedName("Unit")
    @Expose
    var unit: String? = null

    @SerializedName("UnitType")
    @Expose
    var unitType: Int? = null

}