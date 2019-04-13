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
    val THE_API_KEY: String = "VejmG2NDnVAmqS2l0pRabzM5ZldNOilF"
    var rezultatet: List<DailyForecast>? = null
    var prefs: AppPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = AppPreferences(applicationContext)


        if (prefs!!.celsiusOrFahrenheit.equals("")) {
            prefs!!.celsiusOrFahrenheit = celsius!!
            temperatureConverterButton.isChecked = false
        } else {
            if (prefs!!.celsiusOrFahrenheit.equals(celsius!!)) {
                temperatureConverterButton.isChecked = false
            } else {
                temperatureConverterButton.isChecked = true
            }
        }

        getTheResponseBody()

        temperatureConverterButton.setOnCheckedChangeListener { whatIsThis, isChecked ->
            run {
                if (temperatureConverterButton.isChecked) {
                    prefs!!.celsiusOrFahrenheit = fahrenheit!!
                } else {
                    prefs!!.celsiusOrFahrenheit = celsius!!
                }
                setTemperature(
                    rezultatet,
                    0,
                    minTemperaturaTextView,
                    maxTemperaturaTextView,
                    prefs!!.celsiusOrFahrenheit
                )
                adapterOne.notifyDataSetChanged()
            }
        }
    }

    private fun getTheResponseBody() {
        var call = retrofit.create(ApiService::class.java)
            .getForecast(THE_API_KEY)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("DESHTIM NGA getTheResponseBody() throw=" + t.toString() + " PERFUNDOI")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                rezultatet = response.body()!!.dailyForecasts

                setDayAndDate(rezultatet, 0, dataDheDitaTextView)
                setTemperature(
                    rezultatet,
                    0,
                    minTemperaturaTextView,
                    maxTemperaturaTextView,
                    prefs!!.celsiusOrFahrenheit
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
        homeReycler.adapter = adapterOne
    }

    lateinit var adapterOne: DailyListAdapter
}