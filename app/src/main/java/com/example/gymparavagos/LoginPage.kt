package com.example.gymparavagos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.login)
        supportActionBar?.hide()
        val botonLog: Button = findViewById(R.id.botonLogin)
        botonLog.setOnClickListener { login() }
    }

    private fun login() {
        val campoUsuario: String = findViewById<EditText>(R.id.userLogin).text.toString().trim().lowercase()

        Toast.makeText(applicationContext, "Login correcto $campoUsuario", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, UserPage::class.java).putExtra("nameUser", campoUsuario))
        finish()

    }

}