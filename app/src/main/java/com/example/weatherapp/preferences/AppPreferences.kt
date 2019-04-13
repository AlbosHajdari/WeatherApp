package com.example.weatherapp.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    val TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT = "temperatureConverterButtonCelsiusOrFahrenheit"
    val PREFS_KEY = "preferences"

    var preferences: SharedPreferences = context.getSharedPreferences(PREFS_KEY, 0)

    var celsiusOrFahrenheit: String
        get() = preferences!!.getString(TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT, "")
        set(value) = preferences!!.edit().putString(TEMPERATURE_CONVERTER_BUTTON_CELSIUS_OR_FAHRENHEIT, value).apply()
}