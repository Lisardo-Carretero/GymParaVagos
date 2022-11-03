package com.example.gymparavagos


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


@Suppress("CAST_NEVER_SUCCEEDS")
class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val gcf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, gcf)
        val signInIntent = googleClient.signInIntent

        findViewById<ImageButton>(R.id.bt_login).setOnClickListener {
            loginEmail()
        }
        findViewById<SignInButton>(R.id.bt_google).setOnClickListener {
            launcher.launch(signInIntent)
        }
        session()
    }

    private fun incioSesion(name: String, p: ProviderType) {

        startActivity(
            Intent(this, UserPage::class.java).putExtra("email", name)
                .putExtra("provider", p)
        )
        finish()
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        if (email != null && provider != null)
            incioSesion(email, provider as ProviderType)
    }

    private fun loginEmail() {
        val userEditText: EditText = findViewById(R.id.textEdit_user)
        val userEditPass: EditText = findViewById(R.id.textEdit_pass)
        if (userEditText.text.isNotEmpty() && userEditPass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                userEditText.text.toString(),
                userEditPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    incioSesion(userEditText.text.toString(), ProviderType.EMAIL)
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Se ha producido un error")
                    builder.setPositiveButton("Aceptar", null)
                    builder.create()?.show()
                }
            }
        }
    }


    /*lo que antes era oncreateactivity o algo asi. Ahora crea un launcher
     que crea una vista para registrarte con google
     */
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Bienvenido " + account.email, Toast.LENGTH_SHORT).show()
                        incioSesion(account.displayName.toString(), ProviderType.GOOGLE)
                    } else {
                        Toast.makeText(this, "joder INTENT " + it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}



