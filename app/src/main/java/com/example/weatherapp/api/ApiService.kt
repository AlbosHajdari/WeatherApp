package com.example.weatherapp.api

import com.example.weatherapp.responses.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/forecasts/v1/daily/5day/298740")
    fun getForecast(
        @Query("apikey") apiKey: String
    ): Call<ResponseBody>
}