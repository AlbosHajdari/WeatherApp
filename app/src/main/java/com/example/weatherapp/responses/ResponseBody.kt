package com.example.weatherapp.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseBody {
    @SerializedName("Headline")
    @Expose
    var headline: Headline? = null

    @SerializedName("DailyForecasts")
    @Expose
    var dailyForecasts: List<DailyForecast>? = null
}