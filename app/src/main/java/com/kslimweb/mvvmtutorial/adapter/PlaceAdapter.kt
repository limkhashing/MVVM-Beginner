package com.kslimweb.mvvmtutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kslimweb.mvvmtutorial.R
import com.kslimweb.mvvmtutorial.model.Place

class PlaceAdapter(private val places: List<Place>?, private val context: Context) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_place_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return places?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the name of the 'Place'
        places?.let {
            holder.placeName.text = it[position].title

            // Set the image
            val defaultOptions = RequestOptions().error(R.drawable.ic_launcher_background)
            Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(it[position].imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.placeImage)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeImage: ImageView = itemView.findViewById(R.id.image_place)
        val placeName: TextView = itemView.findViewById(R.id.text_place_name)
    }
}