package com.example.weatherapp.detailsOfDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Wind {
    @SerializedName("Speed")
    @Expose
    var speed: Speed? = null

    @SerializedName("Direction")
    @Expose
    var direction: Direction? = null
}