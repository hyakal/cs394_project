package com.example.cs394_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cs394_project.R
import com.example.cs394_project.model.HourlyWeather

class HourlyWeatherAdapter(diffCallback: DiffUtil.ItemCallback<HourlyWeather>) :
    ListAdapter<HourlyWeather, HourlyWeatherAdapter.ItemViewHolder>(diffCallback) {

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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_forecast, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }

    // DiffUtil for efficient updates
    class HourlyWeatherDiffCallback : DiffUtil.ItemCallback<HourlyWeather>() {
        override fun areItemsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
            return oldItem.time == newItem.time // Assuming 'time' uniquely identifies an item
        }

        override fun areContentsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
            return oldItem == newItem
        }
    }
}

