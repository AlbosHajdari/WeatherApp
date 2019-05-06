package com.example.weatherapp

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.adapters.DailyListAdapter
import com.example.weatherapp.api.ApiService
import com.example.weatherapp.api.retrofit
import com.example.weatherapp.preferences.AppPreferences
import com.example.weatherapp.responses.DailyForecast
import com.example.weatherapp.responses.HourlyForecast
import com.example.weatherapp.responses.ResponseBody
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val THE_API_KEY: String = "11CRmW4yNP4icQrfiEcKaWVvXy0ocbA0"
    var rezultatet: List<DailyForecast>? = null
    var rezultatet2: List<HourlyForecast>? = null
    var prefs: AppPreferences? = null
    var listOfCities: Array<String>? = null
    var listOfCitiesNames: ArrayList<String>? = null
    lateinit var adapterOne: DailyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = AppPreferences(applicationContext)

        val res: Resources = resources

        listOfCities = res.getStringArray(R.array.citiesList) as Array<String>

        gettingOnlyTheNamesOfCities()
        showCities()
        citiesSpinner.setSelection(listOfCitiesNames!!.indexOf(prefs!!.lastCity))

        temperatureConverterButton.isChecked = prefs!!.celsiusOrFahrenheit

        temperatureConverterButton.setOnCheckedChangeListener { whatIsThis, isChecked ->
            run {
                setTemperature(
                    rezultatet,
                    0,
                    minTemperaturaTextView,
                    maxTemperaturaTextView,
                    isChecked
                )
                setNowTemperature(
                    rezultatet2!!,
                    0,
                    nowTemperatureTextView,
                    isChecked
                )
                adapterOne.notifyDataSetChanged()
            }
        }
    }

    private fun gettingOnlyTheNamesOfCities() {
        listOfCitiesNames = ArrayList<String>()
        for(i in 0..listOfCities!!.size-1){
            var emriQytetit = listOfCities!!.get(i).split("|").get(0)
            listOfCitiesNames!!.add(emriQytetit)
        }
    }

    private fun showCities() {
        ArrayAdapter.createFromResource(
            this,
            R.array.citiesList,
            android.R.layout.simple_spinner_item
        ).also {
            var adapter = ArrayAdapter<Any>(this, android.R.layout.simple_spinner_dropdown_item, listOfCitiesNames as List<Any>)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            citiesSpinner.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                var qyteti = listOfCities!!.get(pos).split("|")
                get5DaysTheResponseBody(qyteti.get(1))
                getHourlyTheResponseBody(qyteti.get(1))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //TO DO
            }
        }
        citiesSpinner.onItemSelectedListener = SpinnerActivity()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        prefs!!.celsiusOrFahrenheit = temperatureConverterButton.isChecked
        prefs!!.lastCity = citiesSpinner.selectedItem.toString()
        super.onSaveInstanceState(outState)
    }

    private fun get5DaysTheResponseBody(cityKey: String?) {
        var call = retrofit.create(ApiService::class.java)
            .get5dayForecast(cityKey.toString(), THE_API_KEY)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("DESHTIM NGA getTheResponseBody() throw=" + t.toString() + " PERFUNDOI")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("MESAZHI nga 5DAYS = " + response.message())
                println("KODI nga 5DAYS = " + response.code())
                rezultatet = response.body()!!.dailyForecasts

                setDayAndDate(rezultatet, 0, dataDheDitaTextView)
                setTemperature(
                    rezultatet,
                    0,
                    minTemperaturaTextView,
                    maxTemperaturaTextView,
                    temperatureConverterButton.isChecked
                )
                setWeatherDescription(rezultatet, 0, pershkrimiMotitTextView)
                setTheIconImage(rezultatet, 0, iconImageView, applicationContext)

                displayList()

                println("SUKSES NGA getTheResponseBody()")
            }
        })
    }

    private fun getHourlyTheResponseBody(cityKey: String?) {
        var call = retrofit.create(ApiService::class.java)
            .getHoulryForecast(cityKey.toString(), THE_API_KEY)

        call.enqueue(object : Callback<List<HourlyForecast>> {
            override fun onFailure(call: Call<List<HourlyForecast>>, t: Throwable) {
                println("DESHTIM NGA getHourlyTheResponseBody() throw=" + t.toString() + " PERFUNDOI")
            }

            override fun onResponse(call: Call<List<HourlyForecast>>, response: Response<List<HourlyForecast>>) {
                println("MESAZHI nga HOURLY = " + response.message())
                println("KODI nga HOURLY = " + response.code())
                rezultatet2 = response.body()

                println("TEST = " + rezultatet2!!.get(0).temperature + " PERFUNDOI")

                setNowTemperature(
                    rezultatet2!!,
                    0,
                    nowTemperatureTextView,
                    temperatureConverterButton.isChecked
                )

                setNowWeatherDescription(rezultatet2, 0, nowPershkrimiMotitTextView)
                setNowTheIconImage(rezultatet2,0, nowIconImageView, applicationContext)

                println("SUKSES NGA getHourlyTheResponseBody()")
            }
        })
    }

    private fun displayList() {
        val layoutManager = GridLayoutManager(this, 1)
        homeReycler.layoutManager = layoutManager

        val lookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        layoutManager.spanSizeLookup = lookup
        adapterOne = DailyListAdapter()
        adapterOne.results = rezultatet
        adapterOne.temperatureConverterButton2 = temperatureConverterButton
        homeReycler.adapter = adapterOne
    }
}

fun setNowTemperature(
    rezultatet2: List<HourlyForecast>,
    position: Int,
    nowTemperatureTextView: TextView,
    celsiusOrFahrenheit: Boolean
) {
    if ((rezultatet2!!.get(0).temperature!!.unit.equals(celsius) and celsiusOrFahrenheit.equals(true)) or
        (rezultatet2!!.get(0).temperature!!.unit.equals(fahrenheit) and celsiusOrFahrenheit.equals(false))
    ) {
        if (celsiusOrFahrenheit) {
            changeNowValueAndUnitToFahrenheit(rezultatet2)
        } else {
            changeNowValueAndUnitToCelsius(rezultatet2)
        }
    }

    var temperature: String =
        String.nowFormatedValue(rezultatet2!!, position) + "°" +
                rezultatet2!!.get(position).temperature!!.unit

    nowTemperatureTextView!!.text = temperature
}

fun changeNowValueAndUnitToFahrenheit(rezultatet: List<HourlyForecast>?) {
    for (iterator in 0..rezultatet!!.size - 1) {
        rezultatet!!.get(iterator).temperature!!.value =
            Double.celsiusToFahrenheit(rezultatet!!.get(iterator).temperature!!.value!!)

        rezultatet!!.get(iterator).temperature!!.unit = fahrenheit
        rezultatet!!.get(iterator).temperature!!.unitType = 18
    }
}

fun changeNowValueAndUnitToCelsius(rezultatet: List<HourlyForecast>?) {
    for (iterator in 0..rezultatet!!.size - 1) {
        rezultatet!!.get(iterator).temperature!!.value =
            Double.fahrenheitToCelsius(rezultatet!!.get(iterator).temperature!!.value!!)

        rezultatet!!.get(iterator).temperature!!.unit = celsius
        rezultatet!!.get(iterator).temperature!!.unitType = 17
    }
}

fun String.Companion.nowFormatedValue(rezultatet: List<HourlyForecast>, position: Int): String? {
    return formater.format(rezultatet!!.get(position).temperature!!.value)
}


fun setNowWeatherDescription(rezultatet: List<HourlyForecast>?, position: Int, pershkrimiMotitTextView: TextView) {
    pershkrimiMotitTextView!!.text = rezultatet!!.get(position).iconPhrase.toString()
}

fun setNowTheIconImage(rezultatet: List<HourlyForecast>?, position: Int, iconImageView: ImageView, context: Context) {
    var iconNumber: Int = rezultatet!!.get(position)!!.weatherIcon!!

    var iconLink: String
    if (iconNumber < 10) {
        iconLink = "https://www.developer.accuweather.com/sites/default/files/0" + iconNumber + "-s.png"
    } else {
        iconLink = "https://www.developer.accuweather.com/sites/default/files/" + iconNumber + "-s.png"
    }
    Glide.with(context).load(iconLink).into(iconImageView)
}
