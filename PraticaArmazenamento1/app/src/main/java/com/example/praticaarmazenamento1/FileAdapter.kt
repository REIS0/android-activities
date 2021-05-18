package com.example.praticaarmazenamento1

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.file_item.view.*
import java.lang.Exception

class FileAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textFileName: TextView = itemView.text_view_file_name
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FileViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.file_item, p0, false)
        return FileViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: FileViewHolder, p1: Int) {
        val current = list[p1]
        p0.textFileName.text = current
    }

}