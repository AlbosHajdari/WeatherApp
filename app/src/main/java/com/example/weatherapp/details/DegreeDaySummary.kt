package com.example.weatherapp.details

import com.example.weatherapp.detailsOfDetails.Cooling
import com.example.weatherapp.detailsOfDetails.Heating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DegreeDaySummary {


    @SerializedName("Heating")
    @Expose
    var heating: Heating? = null

    @SerializedName("Cooling")
    @Expose
    var cooling: Cooling? = null
}