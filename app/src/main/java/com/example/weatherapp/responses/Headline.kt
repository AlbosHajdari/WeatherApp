package com.example.weatherapp.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Headline {
    @SerializedName("EffectiveDate")
    @Expose
    var effectiveDate: String? = null

    @SerializedName("EffectiveEpochDate")
    @Expose
    var effectiveEpochDate: Int? = null

    @SerializedName("Severity")
    @Expose
    var severity: Int? = null

    @SerializedName("Text")
    @Expose
    var text: String? = null

    @SerializedName("Category")
    @Expose
    var category: String? = null

    @SerializedName("EndDate")
    @Expose
    var endDate: String? = null

    @SerializedName("EndEpochDate")
    @Expose
    var endEpochDate: Int? = null

    @SerializedName("MobileLink")
    @Expose
    var mobileLink: String? = null

    @SerializedName("Link")
    @Expose
    var link: String? = null

}