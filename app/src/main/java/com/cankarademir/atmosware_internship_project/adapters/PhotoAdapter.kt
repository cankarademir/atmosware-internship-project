package com.cankarademir.atmosware_internship_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.models.Photos
import com.cankarademir.atmosware_internship_project.ui.photos.PhotosFragmentDirections

class PhotoAdapter(private val dataList: List<Photos>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View,private val dataList: List<Photos>) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val imageView: ImageView = itemView.findViewById(R.id.image)

        init {
            itemView.setOnClickListener {
                val data = dataList[adapterPosition]
                val action = PhotosFragmentDirections.actionNavigationPhotosToDetailPhotoFragment(data)
                val navController = Navigation.findNavController(itemView)
                navController.navigate(action)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_photo, parent, false)
        return ViewHolder(itemView, dataList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.title
        Glide.with(holder.imageView)
            .load(data.url)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}