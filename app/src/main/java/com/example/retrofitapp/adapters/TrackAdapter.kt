package com.example.retrofitapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.models.Data

class TrackAdapter(var allTracks: MutableList<Data>) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trackId: TextView
        val trackTitle: TextView

        init {
            trackId = view.findViewById(R.id.tackId)
            trackTitle = view.findViewById(R.id.trackTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTrack = allTracks[position]

        holder.trackId.text = currentTrack.id.toString()
        holder.trackTitle.text = currentTrack.title
    }

    override fun getItemCount(): Int {
        return allTracks.size
    }

    fun updateData(data: MutableList<Data>) {
        this.allTracks = data
        this.notifyDataSetChanged()
    }
}