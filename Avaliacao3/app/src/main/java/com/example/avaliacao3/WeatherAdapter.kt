package com.example.avaliacao3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3.model.City
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherAdapter(
    private val weatherList: List<City>
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = weatherList[position]
        holder.textViewCity.text = current.name

        holder.textViewTemperature.text = "${current.main.temp.toInt()}"
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCity = itemView.text_view_city
        val textViewTemperature = itemView.text_view_temperature
    }
}