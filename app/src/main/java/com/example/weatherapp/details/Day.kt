package com.example.weatherapp.details

import com.example.weatherapp.detailsOfDetails.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Day {
    @SerializedName("Icon")
    @Expose
    var icon: Int? = null

    @SerializedName("IconPhrase")
    @Expose
    var iconPhrase: String? = null

    @SerializedName("ShortPhrase")
    @Expose
    var shortPhrase: String? = null

    @SerializedName("LongPhrase")
    @Expose
    var longPhrase: String? = null

    @SerializedName("PrecipitationProbability")
    @Expose
    var precipitationProbability: Int? = null

    @SerializedName("ThunderstormProbability")
    @Expose
    var thunderstormProbability: Int? = null

    @SerializedName("RainProbability")
    @Expose
    var rainProbability: Int? = null

    @SerializedName("SnowProbability")
    @Expose
    var snowProbability: Int? = null

    @SerializedName("IceProbability")
    @Expose
    var iceProbability: Int? = null

    @SerializedName("Wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("WindGust")
    @Expose
    var windGust: WindGust? = null

    @SerializedName("TotalLiquid")
    @Expose
    var totalLiquid: TotalLiquid? = null

    @SerializedName("Rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("Snow")
    @Expose
    var snow: Snow? = null

    @SerializedName("Ice")
    @Expose
    var ice: Ice? = null

    @SerializedName("HoursOfPrecipitation")
    @Expose
    var hoursOfPrecipitation: Int? = null

    @SerializedName("HoursOfRain")
    @Expose
    var hoursOfRain: Int? = null

    @SerializedName("HoursOfSnow")
    @Expose
    var hoursOfSnow: Int? = null

    @SerializedName("HoursOfIce")
    @Expose
    var hoursOfIce: Int? = null

    @SerializedName("CloudCover")
    @Expose
    var cloudCover: Int? = null
}