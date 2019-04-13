package com.example.weatherapp.details

import com.example.weatherapp.detailsOfDetails.Maximum
import com.example.weatherapp.detailsOfDetails.Minimum
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



public class RealFeelTemperatureShade{
    @SerializedName("Minimum")
    @Expose
    var minimum: Minimum? = null

    @SerializedName("Maximum")
    @Expose
    var maximum: Maximum? = null
}