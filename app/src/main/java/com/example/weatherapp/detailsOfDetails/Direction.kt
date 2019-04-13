package com.example.weatherapp.detailsOfDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Direction {
    @SerializedName("Degrees")
    @Expose
    var degrees: Int? = null

    @SerializedName("Localized")
    @Expose
    var localized: String? = null

    @SerializedName("English")
    @Expose
    var english: String? = null
}