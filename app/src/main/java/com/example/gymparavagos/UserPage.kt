package com.example.gymparavagos

import android.R
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.appbar.AppBarLayout


class UserPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.gymparavagos.R.layout.userpage)

        val appbar: androidx.appcompat.widget.Toolbar = findViewById(com.example.gymparavagos.R.id.login_upBar)
        appbar.title = "a"

    }
}
