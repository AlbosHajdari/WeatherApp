package com.example.weatherapp

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.responses.DailyForecast
import java.text.DecimalFormat
import java.text.SimpleDateFormat

val fahrenheit: String? = "F"
val celsius: String? = "C"

fun Double.Companion.fahrenheitToCelsius(fahrenheit: Double): Double? {
    return (fahrenheit - 32.0) * 5.0 / 9.0
}

fun Double.Companion.celsiusToFahrenheit(celsius: Double): Double? {
    return (celsius * 9.0 / 5.0) + 32.0
}

val formater = DecimalFormat("0.0")

fun String.Companion.minimumFormatedValue(rezultatet: List<DailyForecast>, position: Int): String? {
    return formater.format(rezultatet!!.get(position).temperature!!.minimum!!.value)
}

fun String.Companion.maximumFormatedValue(rezultatet: List<DailyForecast>, position: Int): String? {
    return formater.format(rezultatet!!.get(position).temperature!!.maximum!!.value)
}

fun setDayAndDate(rezultatet: List<DailyForecast>?, position: Int, ditaDheDataTextView: TextView) {
    var dataString: String = rezultatet!!.get(position).date!!
    val date = SimpleDateFormat("yyyy-MM-dd").parse(dataString)

    var dateFormat = SimpleDateFormat("EEEE")
    var dayOfTheWeekString = dateFormat.format(date)

    dateFormat = SimpleDateFormat("MMMM")
    var monthString = dateFormat.format(date)

    dateFormat = SimpleDateFormat("dd")
    var dayString = dateFormat.format(date)

    ditaDheDataTextView.text = dayOfTheWeekString + ", " + monthString + " " + dayString
}

fun setTemperature(
    rezultatet: List<DailyForecast>?,
    position: Int,
    minTemperatureTextView: TextView,
    maxTemperatureTextView: TextView,
    celsiusOrFahrenheit: String
) {
    if (!(rezultatet!!.get(0).temperature!!.minimum!!.unit.equals(celsiusOrFahrenheit))) {
        if (celsiusOrFahrenheit.equals(fahrenheit)) {
            changeValueAndUnitToFahrenheit(rezultatet)
        } else {
            changeValueAndUnitToCelsius(rezultatet)
        }
    }

    var minTemperature: String =
        String.minimumFormatedValue(rezultatet!!, position) + "°" +
                rezultatet!!.get(position).temperature!!.minimum!!.unit
    var maxTemperature: String =
        String.maximumFormatedValue(rezultatet!!, position) + "°" +
                rezultatet!!.get(position).temperature!!.maximum!!.unit

    minTemperatureTextView!!.text = minTemperature
    maxTemperatureTextView!!.text = maxTemperature
}

fun setWeatherDescription(rezultatet: List<DailyForecast>?, position: Int, pershkrimiMotitTextView: TextView) {
    pershkrimiMotitTextView!!.text = rezultatet!!.get(position).day!!.iconPhrase.toString()
}

fun setTheIconImage(rezultatet: List<DailyForecast>?, position: Int, iconImageView: ImageView, context: Context) {
    var iconNumber: Int = rezultatet!!.get(position)!!.day!!.icon!!

    var iconLink: String
    if (iconNumber < 10) {
        iconLink = "https://www.developer.accuweather.com/sites/default/files/0" + iconNumber + "-s.png"
    } else {
        iconLink = "https://www.developer.accuweather.com/sites/default/files/" + iconNumber + "-s.png"
    }
    Glide.with(context).load(iconLink).into(iconImageView)
}

fun changeValueAndUnitToFahrenheit(rezultatet: List<DailyForecast>?) {
    for (iterator in 0..rezultatet!!.size - 1) {
        rezultatet!!.get(iterator).temperature!!.minimum!!.value =
            Double.celsiusToFahrenheit(rezultatet!!.get(iterator).temperature!!.minimum!!.value!!)
        rezultatet!!.get(iterator).temperature!!.maximum!!.value =
            Double.celsiusToFahrenheit(rezultatet!!.get(iterator).temperature!!.maximum!!.value!!)

        rezultatet!!.get(iterator).temperature!!.minimum!!.unit = fahrenheit
        rezultatet!!.get(iterator).temperature!!.minimum!!.unitType = 18
        rezultatet!!.get(iterator).temperature!!.maximum!!.unit = fahrenheit
        rezultatet!!.get(iterator).temperature!!.maximum!!.unitType = 18
    }
}

fun changeValueAndUnitToCelsius(rezultatet: List<DailyForecast>?) {
    for (iterator in 0..rezultatet!!.size - 1) {
        rezultatet!!.get(iterator).temperature!!.minimum!!.value =
            Double.fahrenheitToCelsius(rezultatet!!.get(iterator).temperature!!.minimum!!.value!!)
        rezultatet!!.get(iterator).temperature!!.maximum!!.value =
            Double.fahrenheitToCelsius(rezultatet!!.get(iterator).temperature!!.maximum!!.value!!)

        rezultatet!!.get(iterator).temperature!!.minimum!!.unit = celsius
        rezultatet!!.get(iterator).temperature!!.minimum!!.unitType = 17
        rezultatet!!.get(iterator).temperature!!.maximum!!.unit = celsius
        rezultatet!!.get(iterator).temperature!!.maximum!!.unitType = 17
    }
}