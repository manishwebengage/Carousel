package com.webengage.carousel.viewpager.viewpager2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.webengage.carousel.R

class ViewPager2Adapter(private val imageUrls: List<String>) : RecyclerView.Adapter<ViewPager2Adapter.ImageViewHolder>() {

    private val newList: List<String> =
        listOf(imageUrls.last()) + imageUrls + listOf(imageUrls.first())

    // Create a ViewHolder to hold the ImageView
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Inflate the item layout for each page
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // Get the image URL at the current position
        val imageUrl = newList[position]

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(imageUrl)  // URL or file path to the image
            .into(holder.imageView)  // ImageView to load the image into
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}
