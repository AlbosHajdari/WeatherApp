package com.example.weatherapp.responses

import com.example.weatherapp.details.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.weatherapp.detailsOfDetails.Ice
import com.example.weatherapp.detailsOfDetails.Snow
import com.example.weatherapp.detailsOfDetails.Rain
import com.example.weatherapp.detailsOfDetails.TotalLiquid
import com.example.weatherapp.detailsOfDetails.WindGust
import com.example.weatherapp.detailsOfDetails.Wind


class HourlyForecast {
    @SerializedName("DateTime")
    @Expose
    val dateTime: String? = null

    @SerializedName("EpochDateTime")
    @Expose
    val epochDateTime: Int? = null

    @SerializedName("WeatherIcon")
    @Expose
    val weatherIcon: Int? = null

    @SerializedName("IconPhrase")
    @Expose
    val iconPhrase: String? = null

    @SerializedName("IsDaylight")
    @Expose
    val isDaylight: Boolean? = null

    @SerializedName("Temperature")
    @Expose
    val temperature: Temperature_? = null

    @SerializedName("RealFeelTemperature")
    @Expose
    val realFeelTemperature: RealFeelTemperature_? = null

    @SerializedName("WetBulbTemperature")
    @Expose
    val wetBulbTemperature: WetBulbTemperature? = null

    @SerializedName("DewPoint")
    @Expose
    val dewPoint: DewPoint? = null

    @SerializedName("Wind")
    @Expose
    val wind: Wind? = null

    @SerializedName("WindGust")
    @Expose
    val windGust: WindGust? = null

    @SerializedName("RelativeHumidity")
    @Expose
    val relativeHumidity: Int? = null

    @SerializedName("Visibility")
    @Expose
    val visibility: Visibility? = null

    @SerializedName("Ceiling")
    @Expose
    val ceiling: Ceiling? = null

    @SerializedName("UVIndex")
    @Expose
    val uVIndex: Int? = null

    @SerializedName("UVIndexText")
    @Expose
    val uVIndexText: String? = null

    @SerializedName("PrecipitationProbability")
    @Expose
    val precipitationProbability: Int? = null

    @SerializedName("RainProbability")
    @Expose
    val rainProbability: Int? = null

    @SerializedName("SnowProbability")
    @Expose
    val snowProbability: Int? = null

    @SerializedName("IceProbability")
    @Expose
    val iceProbability: Int? = null

    @SerializedName("TotalLiquid")
    @Expose
    val totalLiquid: TotalLiquid? = null

    @SerializedName("Rain")
    @Expose
    val rain: Rain? = null

    @SerializedName("Snow")
    @Expose
    val snow: Snow? = null

    @SerializedName("Ice")
    @Expose
    val ice: Ice? = null

    @SerializedName("CloudCover")
    @Expose
    val cloudCover: Int? = null

    @SerializedName("MobileLink")
    @Expose
    val mobileLink: String? = null

    @SerializedName("Link")
    @Expose
    val link: String? = null

}