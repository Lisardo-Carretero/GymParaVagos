package com.example.gymparavagos


import android.os.Bundle
import android.view.ActionMode
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


class ElegirTablaActivity : AppCompatActivity() {
    private var actionMode: ActionMode? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.elegir_tabla)
        supportActionBar?.hide()
       Toast.makeText(applicationContext, "" + intent.extras?.getString("fecha"), Toast.LENGTH_SHORT).show()
        /*val actionBar: ActionBar = supportActionBar!!
        actionBar.title = ""
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)*/
    }
}

