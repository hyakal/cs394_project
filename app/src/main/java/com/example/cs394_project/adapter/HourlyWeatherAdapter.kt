package com.example.cs394_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs394_project.R
import com.example.cs394_project.model.HourlyWeather

class HourlyWeatherAdapter(private var data: List<HourlyWeather>) : RecyclerView.Adapter<HourlyWeatherAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val tempTextView: TextView = view.findViewById(R.id.tempTextView)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)

        fun bind(weather: HourlyWeather) {
            timeTextView.text = weather.time
            tempTextView.text = weather.temperature
            weatherIcon.setImageResource(weather.iconResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_forecast, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val weather = data[position]
        holder.bind(weather)
    }

    fun updateData(newData: List<HourlyWeather>) {
        data = newData
        notifyDataSetChanged()
    }
}