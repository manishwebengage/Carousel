package com.webengage.carousel.custom_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.webengage.carousel.main.CarouselModel
import com.webengage.carousel.databinding.ItemCustomRecyclerBinding

class CustomRecyclerAdapter: ListAdapter<CarouselModel, MainViewHolder>(
    object : DiffUtil.ItemCallback<CarouselModel>() {
        override fun areItemsTheSame(oldItem: CarouselModel, newItem: CarouselModel): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: CarouselModel, newItem: CarouselModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent)
    }
}

class MainViewHolder(
    private val binding: ItemCustomRecyclerBinding,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: CarouselModel) {
        Glide.with(binding.root).load(model.imageUrl).into(binding.image)
    }

    companion object {
        fun create(parent: ViewGroup): MainViewHolder {
            return MainViewHolder(
                ItemCustomRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
        }
    }
}