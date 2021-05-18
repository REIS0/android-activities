package com.example.praticaarmazenamento1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.file_item.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val fileList: MutableList<String> = ArrayList()
    private val adapter = FileAdapter(fileList)
    private var internal = true
    private var external = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        if (internal) {
            fileList.addAll(fileList())
            adapter.notifyDataSetChanged()
        } else {
            val file = File(getExternalFilesDir(null), "")
            fileList.clear()
            fileList.addAll(file.list())
            adapter.notifyDataSetChanged()
        }

        actionBar?.title = "Exercício Arm. Interno e Externo"
    }

    fun addItem(view: View) {
        val name = text_input_file_name.text.toString()
        val content = text_input_file_content.text.toString()
        if (internal) {
            val fileSave = File(filesDir, name)
            fileSave.createNewFile()
            fileSave.printWriter().use { out -> out.println(content) }
        } else {
            val fileSave = File(getExternalFilesDir(null), name)
            fileSave.createNewFile()
            fileSave.printWriter().use { out -> out.println(content) }
        }
        fileList.add(name)
        adapter.notifyItemInserted(fileList.lastIndex)
    }

    fun setInternal(view: View) {
        internal = true
        external = false
        fileList.clear()
        fileList.addAll(fileList())
        adapter.notifyDataSetChanged()
    }

    fun setExternal(view: View) {
        internal = false
        external = true
        val file = File(getExternalFilesDir(null), "")
        fileList.clear()
        fileList.addAll(file.list())
        adapter.notifyDataSetChanged()
    }

    fun deleteItem(view: View) {
        val file = text_view_file_name.text.toString()
        val fileIndex = fileList.indexOf(file)
        if (internal) {
            val fileDelete = File(filesDir, file)
            fileDelete.delete()
        } else {
            val fileDelete = File(getExternalFilesDir(null), file)
            fileDelete.delete()
        }
        fileList.removeAt(fileIndex)
        adapter.notifyItemRemoved(fileIndex)
    }
}