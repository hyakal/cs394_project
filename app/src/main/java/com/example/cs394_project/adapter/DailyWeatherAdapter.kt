package com.example.cs394_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs394_project.R
import com.example.cs394_project.model.DailyWeather

class DailyWeatherAdapter(private val data: List<DailyWeather>) : RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>() {
    class DailyWeatherViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val weatherIcon: ImageView = itemView.findViewById(R.id.dailyWeatherIcon)
        val tempTextView: TextView = itemView.findViewById(R.id.dailyTempTextView)

        fun bind(weather: DailyWeather) {
            dayTextView.text = weather.day
            tempTextView.text = "${weather.lowTemp}° / ${weather.highTemp}°"
            weatherIcon.setImageResource(weather.iconResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyWeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val dailyWeather = data[position]
        holder.bind(dailyWeather)
    }

}
