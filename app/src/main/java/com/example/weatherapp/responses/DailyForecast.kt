package com.example.weatherapp.responses

import com.example.weatherapp.details.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


public class DailyForecast{
    @SerializedName("Date")
    @Expose
    var date: String? = null

    @SerializedName("EpochDate")
    @Expose
    var epochDate: Int? = null

    @SerializedName("Sun")
    @Expose
    var sun: Sun? = null

    @SerializedName("Moon")
    @Expose
    var moon: Moon? = null

    @SerializedName("Temperature")
    @Expose
    var temperature: Temperature? = null

    @SerializedName("RealFeelTemperature")
    @Expose
    var realFeelTemperature: RealFeelTemperature? = null

    @SerializedName("RealFeelTemperatureShade")
    @Expose
    var realFeelTemperatureShade: RealFeelTemperatureShade? = null

    @SerializedName("HoursOfSun")
    @Expose
    var hoursOfSun: Double? = null

    @SerializedName("DegreeDaySummary")
    @Expose
    var degreeDaySummary: DegreeDaySummary? = null

    @SerializedName("AirAndPollen")
    @Expose
    var airAndPollen: List<AirAndPollen>? = null

    @SerializedName("Day")
    @Expose
    var day: Day? = null

    @SerializedName("Night")
    @Expose
    var night: Night? = null

    @SerializedName("Sources")
    @Expose
    var sources: List<String>? = null

    @SerializedName("MobileLink")
    @Expose
    var mobileLink: String? = null

    @SerializedName("Link")
    @Expose
    var link: String? = null
}