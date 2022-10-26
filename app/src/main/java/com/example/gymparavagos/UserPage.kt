package com.example.gymparavagos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.datepicker.MaterialCalendar

class UserPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userpage)

        findViewById<TextView>(R.id.nombreUsuarioLogeado).text = intent.extras?.getString("nameUser") ?: String()
        val calendario = findViewById<CalendarView>(R.id.calendarTrain)

        // on below line we are adding set on
        // date change listener for calendar view.
        calendario.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                // In this Listener we are getting values
                // such as year, month and day of month
                // on below line we are creating a variable
                // in which we are adding all the variables in it.
                val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                // set this date in TextView for Display
                startActivity(Intent(this, CrearTablaActivity::class.java).putExtra("fecha", date))
            })

    }
}




