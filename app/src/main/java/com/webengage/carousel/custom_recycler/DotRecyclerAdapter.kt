package com.webengage.carousel.custom_recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.webengage.carousel.R

class DotRecyclerAdapter(var context: Context, private var arrayList: ArrayList<String>) :
    RecyclerView.Adapter<DotRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_custom_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualPosition = position % arrayList.size
        holder.onBind(arrayList[actualPosition])
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.image)

        fun onBind(imageUrl : String) {
            Glide.with(context).load(imageUrl).into(imageView)
        }
    }

}