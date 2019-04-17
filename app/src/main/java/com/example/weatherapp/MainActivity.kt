package com.example.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.weatherapp.adapters.DailyListAdapter
import com.example.weatherapp.api.ApiService
import com.example.weatherapp.api.retrofit
import com.example.weatherapp.preferences.AppPreferences
import com.example.weatherapp.responses.DailyForecast
import com.example.weatherapp.responses.ResponseBody
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val THE_API_KEY: String = "11CRmW4yNP4icQrfiEcKaWVvXy0ocbA0"
    var rezultatet: List<DailyForecast>? = null
    var cities: ArrayList<City>? = null
    var prefs: AppPreferences? = null
    lateinit var adapterOne: DailyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCities()

        prefs = AppPreferences(applicationContext)
        temperatureConverterButton.isChecked = prefs!!.celsiusOrFahrenheit

        getTheResponseBody()

        temperatureConverterButton.setOnCheckedChangeListener { whatIsThis, isChecked -> run {
                setTemperature(
                    rezultatet,
                    0,
                    minTemperaturaTextView,
                    maxTemperaturaTextView,
                    isChecked
                )
                adapterOne.notifyDataSetChanged()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        prefs!!.celsiusOrFahrenheit = temperatureConverterButton.isChecked
        super.onSaveInstanceState(outState)
    }

    private fun addCities() {

        var prishtina = City("Prishtina", "298740")
        var tirana = City("Tirana", "6522")
        var shkupi = City("Skopje", "227397")
        var roma = City("Rome", "213490")
        var berlini = City("Berlin", "178087")
        var newyorku = City("New York", "349727")
        var londra = City("London", "328328")
        var stokholmi = City("Stockholm", "314929")

        cities = ArrayList<City>()
        cities!!.add(prishtina)
        cities!!.add(tirana)
        cities!!.add(shkupi)
        cities!!.add(roma)
        cities!!.add(berlini)
        cities!!.add(newyorku)
        cities!!.add(londra)
        cities!!.add(stokholmi)

        var qytetiGjetur = cities!!.find { it.cityKey.equals(prefs!!.lastCityKey) }

        println("EMRI I QYTETIT = " + qytetiGjetur!!.cityName)
        println("KEY I QYTETIT = " +qytetiGjetur.cityKey)
    }

    private fun getTheResponseBody() {
        var call = retrofit.create(ApiService::class.java)
            .getForecast("298740",THE_API_KEY)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("DESHTIM NGA getTheResponseBody() throw=" + t.toString() + " PERFUNDOI")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("MESAZHI = " + response.message())
                println("KODI = " + response.code())
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