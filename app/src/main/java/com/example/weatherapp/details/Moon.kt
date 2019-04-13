package com.example.weatherapp.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



public class Moon{
    @SerializedName("Rise")
    @Expose
    var rise: String? = null


    @SerializedName("EpochRise")
    @Expose
    var epochRise: Int? = null

    @SerializedName("Set")
    @Expose
    var set: String? = null

    @SerializedName("EpochSet")
    @Expose
    var epochSet: Int? = null

    @SerializedName("Phase")
    @Expose
    var phase: String? = null

    @SerializedName("Age")
    @Expose
    var age: Int? = null

}