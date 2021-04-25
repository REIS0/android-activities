package com.example.avaliacao1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.fruta_item.view.*

class FrutaAdapter(private val frutaList: List<Fruta>, private val context: Activity) : RecyclerView.Adapter<FrutaAdapter.FrutaViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FrutaViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.fruta_item, p0, false)
        return FrutaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return frutaList.size
    }

    override fun onBindViewHolder(p0: FrutaViewHolder, p1: Int) {
        val current = frutaList[p1]
        p0.textViewNome.text = current.nome
        p0.textViewBeneficios.text = current.beneficios
        p0.imageButton.setImageURI(current.imagem)
    }

    inner class FrutaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNome: TextView = itemView.text_view_nome
        val textViewBeneficios: TextView = itemView.text_view_beneficios
        val imageButton: ImageButton = itemView.image_button

        init {
            imageButton.setOnClickListener {
                val intent = Intent(context, DetalhesActivity::class.java)
                intent.putExtra("fruta", frutaList[adapterPosition])
                intent.putExtra("position", adapterPosition)
                context.startActivityForResult(intent, 2)
            }
        }

    }
}