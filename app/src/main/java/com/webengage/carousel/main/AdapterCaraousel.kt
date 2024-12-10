package com.webengage.carousel.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.webengage.carousel.R
import com.webengage.carousel.databinding.ItemCarouselBinding


class AdapterCarousel : ListAdapter<String, AdapterCarousel.CustomViewHolder>(customDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCarouselBinding>(layoutInflater,
            R.layout.item_carousel, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }

    inner class CustomViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val customDiffUtils = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}