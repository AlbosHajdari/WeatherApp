package com.example.weatherapp.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    val TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT = "temperatureConverterButtonCelsiusOrFahrenheit"
    val LAST_CITY_NAME = "lastCityName"
    val PREFS_KEY = "preferences"

    var preferences: SharedPreferences = context.getSharedPreferences(PREFS_KEY, 0)

    var celsiusOrFahrenheit: Boolean
        get() = preferences!!.getBoolean(TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT, false)
        set(value) = preferences!!.edit().putBoolean(TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT, value).apply()

    var lastCity: String
        get() = preferences!!.getString(LAST_CITY_NAME, "Prishtina|298740")
        set(value) = preferences!!.edit().putString(LAST_CITY_NAME, value).apply()
}