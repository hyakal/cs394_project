package com.example.cs394_project.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs394_project.R
import com.example.cs394_project.model.District

class DistrictAdapter(
    private val districtList: List<District>,
    private val onItemClick: (District) -> Unit
) : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    // ViewHolder class to hold the references to views
    class DistrictViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val districtNameTextView: TextView = itemView.findViewById(R.id.textViewDistrictName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_district, parent, false)
        return DistrictViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val district = districtList[position]
        holder.districtNameTextView.text = district.name
        holder.itemView.setOnClickListener { onItemClick(district) }
    }

    override fun getItemCount() = districtList.size
}
