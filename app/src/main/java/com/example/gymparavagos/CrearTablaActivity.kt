package com.example.gymparavagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.io.FileOutputStream

class CrearTablaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_tabla)
    // agregaListaDesplegable()
}
/*private fun saveFile() {
  val editText userName

  val textoASalvar: String = .getText().toString()
  var fileOutputStream: FileOutputStream? = null
  try {
      fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)
      fileOutputStream.write(textoASalvar.toByteArray())
      Log.d("TAG1", "Fichero Salvado en: $filesDir/$FILE_NAME")
  } catch (e: Exception) {
      e.printStackTrace()
  } finally {
      if (fileOutputStream != null) {
          try {
              fileOutputStream.close()
          } catch (e: Exception) {
              e.printStackTrace()
          }
      }
  }
}
private fun agregaListaDesplegable(act:String) {

}*/
}