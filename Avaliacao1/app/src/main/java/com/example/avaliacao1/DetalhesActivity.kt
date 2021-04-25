package com.example.avaliacao1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : AppCompatActivity() {
    private var excluir = false
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        setSupportActionBar(custom_toolbar_det)

        val intent = getIntent()
        val fruta = intent.getParcelableExtra<Fruta>("fruta")
        if (fruta != null) {
            text_view_nome_detalhe.text = fruta.nome
            text_view_beneficios_detalhe.text = fruta.beneficios
            supportActionBar?.setTitle(fruta.nome)
            // TODO: persistent
//            image_view_imagem.setImageURI(fruta.imagem)
        }
        pos = intent.getIntExtra("position", pos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    fun close(view: View) {
//        val returnIntent = Intent()
//        setResult(Activity.RESULT_CANCELED, returnIntent)
//        finish()
//    }

    fun deleteItem(view: View) {
        val returnIntent = Intent()
        returnIntent.putExtra("delete", excluir)
        returnIntent.putExtra("position", pos)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}