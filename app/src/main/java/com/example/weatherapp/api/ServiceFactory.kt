package com.example.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var retrofit = Retrofit.Builder()
    .baseUrl("http://dataservice.accuweather.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()