package com.example.gymparavagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.EditText
import android.widget.TextView

class UserPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userpage)

        findViewById<TextView>(R.id.nombreUsuarioLogeado).text = intent.extras?.getString("nameUser") ?: String()
    }

}