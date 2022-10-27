package com.example.gymparavagos

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ElegirTabla : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.elegir_tabla)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return intent?.let {
                val selectedItems = ArrayList<Int>() // Where we track the selected items
                val builder = AlertDialog.Builder(this)
                // Set the dialog title
                builder.setTitle(R.string.Tablas)
                    // Specify the list array, the items to be selected by default (null for none),
                    // and the listener through which to receive callbacks when items are selected
                    .setMultiChoiceItems(R.array.arrayTablas, null,
                        DialogInterface.OnMultiChoiceClickListener { _, which, isChecked ->
                            if (isChecked) {
                                // If the user checked the item, add it to the selected items
                                selectedItems.add(which)
                            } else if (selectedItems.contains(which)) {
                                // Else, if the item is already in the array, remove it
                                selectedItems.remove(which)
                            }
                        })
                    // Set the action buttons
                    .setPositiveButton(R.string.ok,
                        DialogInterface.OnClickListener { _, _ ->
                            // User clicked OK, so save the selectedItems results somewhere
                            // or return them to the component that opened the dialog
                            Toast.makeText(applicationContext, "ha pasado algo", Toast.LENGTH_SHORT).show()
                        })
                    .setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { _, _ ->
                            Toast.makeText(applicationContext, "o no", Toast.LENGTH_SHORT).show()
                        })

                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}