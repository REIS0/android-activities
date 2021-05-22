package com.example.praticaarmazenamentcompartilhado

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.image_layout.view.*

class ImageAdapter(private val imageList: List<Image>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.name_textView
        val imageViewImage: ImageView = itemView.image_imageView
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.image_layout, p0, false)
        return ImageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(p0: ImageViewHolder, p1: Int) {
        val current = imageList[p1]
        p0.imageViewImage.setImageURI(current.uri)
        p0.textViewName.text = current.name
    }
}