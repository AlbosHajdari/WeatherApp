package com.example.weatherapp.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.*
import com.example.weatherapp.preferences.AppPreferences
import com.example.weatherapp.responses.DailyForecast


class DailyListAdapter : RecyclerView.Adapter<DailyListAdapter.ViewHolder>() {

    lateinit var temperatureConverterButton2: SwitchCompat
    var results: List<DailyForecast>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var prefs: AppPreferences? = null

    override fun onCreateViewHolder(parentViewGroupViewGroup: ViewGroup, position: Int): ViewHolder {
        var v = LayoutInflater.from(parentViewGroupViewGroup.context)
            .inflate(R.layout.list_item, parentViewGroupViewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return results!!.size - 1
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, p0: Int) {
        var position = p0 + 1

        prefs = AppPreferences(viewHolder.itemView.context)

        setDayAndDate(results, position, viewHolder.ditaDheDataTextViewListItem)
        setTemperature(
            results,
            position,
            viewHolder.minTemperaturaTextViewListItem,
            viewHolder.maxTemperaturaTextViewListItem,
            temperatureConverterButton2.isChecked
        )
        setWeatherDescription(results, position, viewHolder.pershkrimiMotitTextViewListItem)
        setTheIconImage(results, position, viewHolder.iconImageViewListItem, viewHolder.itemView.context)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ditaDheDataTextViewListItem = itemView.findViewById<TextView>(R.id.ditaDheDataTextViewListItem)
        val minTemperaturaTextViewListItem = itemView.findViewById<TextView>(R.id.minTemperaturaTextViewListItem)
        val maxTemperaturaTextViewListItem = itemView.findViewById<TextView>(R.id.maxTemperaturaTextViewListItem)
        val pershkrimiMotitTextViewListItem = itemView.findViewById<TextView>(R.id.pershkrimiMotitTextViewListItem)
        val iconImageViewListItem = itemView.findViewById<ImageView>(R.id.iconImageViewListItem)
        val parentLinearLayout = itemView.findViewById<ConstraintLayout>(R.id.parentConstraintLayout)
    }
}