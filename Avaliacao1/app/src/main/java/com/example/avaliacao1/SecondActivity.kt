package com.example.avaliacao1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private var imageUri: Uri? = null
    private var SELECT_PIC = 10
    private val main = MainActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setSupportActionBar(custom_toolbar_sec)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun addImage(view: View) {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Selecionar Imagem"), SELECT_PIC)
    }

    fun confirmItem(view: View) {
        val nome = edit_text_nome.text.toString()
        val beneficios = edit_text_beneficios.text.toString()
        val fruta = Fruta(nome, beneficios, imageUri, main.insertion)
        main.insertion++
        val returnIntent = Intent()
        returnIntent.putExtra("fruta", fruta)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PIC) {
                imageUri = data?.data
                image_view.setImageURI(imageUri)
            }
        }
    }
}