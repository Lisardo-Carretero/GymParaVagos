package com.example.gymparavagos


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast


class CrearTablaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.elegir_tabla)
        Toast.makeText(applicationContext, ""+intent.extras?.getString("fecha"), Toast.LENGTH_SHORT).show()

    }

}