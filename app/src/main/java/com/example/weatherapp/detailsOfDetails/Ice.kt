package com.example.weatherapp.detailsOfDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



public class Ice{
    @SerializedName("Value")
    @Expose
    var value: Int? = null

    @SerializedName("Unit")
    @Expose
    var unit: String? = null

    @SerializedName("UnitType")
    @Expose
    var unitType: Int? = null
}