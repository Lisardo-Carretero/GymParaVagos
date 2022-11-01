package com.example.gymparavagos


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginPage : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.gymparavagos.R.layout.login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.serverId))
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<ImageButton>(R.id.bt_login).setOnClickListener {
            setup()
        }

        findViewById<SignInButton>(com.example.gymparavagos.R.id.bt_google).setOnClickListener {
            signInGoogle()
        }
    }

    private fun setup() {
        val userEditText: EditText = findViewById(R.id.textEdit_user)
        val userEditPass: EditText = findViewById(R.id.textEdit_pass)
        if (userEditText.text.isNotEmpty() && userEditPass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                userEditText.text.toString(),
                userEditPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(
                        Intent(this, UserPage::class.java).putExtra("email", userEditText.text.toString())
                    )
                    finish()
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
            handleResults(task)
        }
    }

    private fun signInGoogle() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setAvailableProviders(providers)
            .build()
        launcher.launch(signInIntent)
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, "joder TASK " + task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Bienvenido " + account.email, Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, UserPage::class.java)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
                finish()
            } else {
                startActivity(Intent(this, LoginPage::class.java))
                finish()
                Toast.makeText(this, "joder INTENT " + it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
