package com.example.gymparavagos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    EMAIL, GOOGLE
}

class UserPage : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userpage)
        updateUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection

        return when (item.itemId) {
            R.id.bt_logout -> {
                Toast.makeText(applicationContext, "AAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI() {
        this.actionBar?.title = intent.extras?.getString("email") ?: ""
    }
}
