package com.example.gymparavagos

import android.R
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class UserPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.gymparavagos.R.layout.userpage)
        auth = FirebaseAuth.getInstance()

        val appbar: androidx.appcompat.widget.Toolbar = findViewById(com.example.gymparavagos.R.id.user_upBar)
        appbar.title = intent.getStringExtra("name")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            com.example.gymparavagos.R.id.bt_logout -> {
                auth.signOut()
                startActivity(Intent(this, LoginPage::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
