package com.nikita.weathercleanarch.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.domain.entities.Weather
import com.nikita.weathercleanarch.R
import kotlinx.android.synthetic.main.view_weather_list_item.view.*

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weather = mutableListOf<Weather>()

    fun initData(weather: List<Weather>) {
        this.weather.addAll(weather)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_weather_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.weatherState.text = holder.itemView.context.getString(R.string.weather, weather[position].weather_state_name)
        holder.temp.text = holder.itemView.context.getString(R.string.temperature, weather[position].the_temp.toString())
        holder.date.text = holder.itemView.context.getString(R.string.date, weather[position].applicable_date)
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val weatherState = itemView.weatherState
        val temp = itemView.temp
        val date = itemView.date
    }
}