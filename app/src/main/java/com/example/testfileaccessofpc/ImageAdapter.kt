package com.example.testfileaccessofpc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(
    private val imageList: List<String>,
    private val baseUrl: String,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageList[position]
        // Use Glide to load images into ImageView
        Glide.with(holder.itemView.context)
            .load("$baseUrl/$imageUrl")
            .into(holder.imageView)

        // Set click listener to open ImageViewerActivity
        holder.itemView.setOnClickListener {
            onClick(imageUrl)
        }
    }

    override fun getItemCount(): Int = imageList.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}





























//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//
//class ImageAdapter(
//    private val imageList: List<String>,
//    private val baseUrl: String,
//    private val onItemClick: (String) -> Unit
//) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
//
//    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        val imageUrl = "$baseUrl/${imageList[position]}"
//        Glide.with(holder.itemView.context).load(imageUrl).into(holder.imageView)
//
//        holder.itemView.setOnClickListener {
//            onItemClick(imageUrl)
//        }
//    }
//
//    override fun getItemCount(): Int = imageList.size
//}