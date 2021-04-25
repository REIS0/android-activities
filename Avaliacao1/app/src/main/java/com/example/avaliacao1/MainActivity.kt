package com.example.avaliacao1

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val frutaList: MutableList<Fruta> = ArrayList()
    private val adapter = FrutaAdapter(frutaList, this)
    var insertion = 0
//    private val filterDialog = AlertDialog.Builder(this).setTitle("Filtro").create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(custom_toolbar_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    }

    fun addItem(view: View) {
        add_button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val fruta = data?.getParcelableExtra<Fruta>("fruta")
                if (fruta != null) {
                    frutaList.add(fruta)
                }
                adapter.notifyItemInserted(frutaList.indexOf(fruta))
            }
            if (requestCode == 2) {
                val delete = data?.getBooleanExtra("delete", false)
                // que negocio horroroso
                // ! nao funciona
                val position = data?.getIntExtra("position", -1).toString().toInt()
                if (delete == true && position != -1) {
                    frutaList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.filter_btn) {
//            filterDialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}