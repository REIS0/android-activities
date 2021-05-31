package com.example.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // sim nome horrivel okay entendi
    val SHARED_PREFERENCE_NAME = "my_shared_preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("Files")

        button_escrever.setOnClickListener {
            saveData()
        }

        button_ler.setOnClickListener {
            loadData()
        }
    }


    private fun saveData() {
        val nome = edit_text_nome.text.toString()
        val idade = edit_text_idade.text.toString().toInt()

        val prefs = getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
        val editor = prefs.edit()

        editor.apply {
            putString("nome", nome)
            putInt("idade", idade)
            apply()
        }

        edit_text_nome.text.clear()
        edit_text_idade.text.clear()
    }

    private fun loadData() {
        val prefs = getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
        val nome = prefs.getString("nome", "???")
        val idade = prefs.getInt("idade", 0)

        text_view_visualizacao.text = "O seu nome é $nome, e sua idade é $idade"
    }
}