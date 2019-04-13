package com.example.weatherapp.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class AirAndPollen {

    @SerializedName("Name")
    @Expose
    var name: String? = null

    @SerializedName("Value")
    @Expose
    var value: Int? = null

    @SerializedName("Category")
    @Expose
    var category: String? = null

    @SerializedName("CategoryValue")
    @Expose
    var categoryValue: Int? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null
}