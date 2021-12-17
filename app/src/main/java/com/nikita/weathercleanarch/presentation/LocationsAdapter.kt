package com.nikita.weathercleanarch.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikita.domain.entities.Location
import com.nikita.weathercleanarch.R
import kotlinx.android.synthetic.main.view_locations_list_item.view.*

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    interface ActionClickListener {
        fun onItemClicked(location: Location)
    }

    private var locations = mutableListOf<Location>()
    var actionListener: ActionClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(locations: List<Location>) {
        if (locations.isEmpty()) return
        this.locations.clear()
        this.locations.addAll(locations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_locations_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.locationTitle.text = locations[position].title
        holder.itemView.setOnClickListener { actionListener?.onItemClicked(locations[position]) }
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    class LocationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationTitle: TextView = itemView.locationTitle
    }
}