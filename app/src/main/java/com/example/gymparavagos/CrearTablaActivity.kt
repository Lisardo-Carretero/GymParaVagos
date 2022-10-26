package com.example.gymparavagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.FileOutputStream

class CrearTablaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_tabla)
        Toast.makeText(applicationContext, ""+intent.extras?.getString("fecha"), Toast.LENGTH_SHORT).show()
    }

}