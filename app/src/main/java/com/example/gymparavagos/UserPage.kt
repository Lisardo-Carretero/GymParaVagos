package com.example.gymparavagos


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    EMAIL, GOOGLE
}

@Suppress("NAME_SHADOWING")
class UserPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userpage)
        updateUI()
        //Guardar datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        this.actionBar?.title = intent.extras?.get("email").toString()
        prefs.putString("email", intent.extras?.getString("email"))
        prefs.apply()
        findViewById<Button>(R.id.button2).setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bt_logout -> {  //Desvinculamos la cuenta
                Toast.makeText(applicationContext, "AAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()
                startActivity(Intent(this, LoginPage::class.java))
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI() {
        this.actionBar?.title = intent.extras?.getString("email") ?: ""
    }
}
