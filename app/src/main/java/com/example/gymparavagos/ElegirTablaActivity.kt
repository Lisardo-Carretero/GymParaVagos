package com.example.gymparavagos


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

import android.widget.Toast




class ElegirTablaActivity : AppCompatActivity() {
    private var actionMode: ActionMode? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.elegir_tabla)
        Toast.makeText(applicationContext, "" + intent.extras?.getString("fecha"), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }
}

