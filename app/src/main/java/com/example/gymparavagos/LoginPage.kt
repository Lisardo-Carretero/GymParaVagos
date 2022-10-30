package com.example.gymparavagos

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.zip.GZIPOutputStream


class LoginPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.login)
        supportActionBar?.hide()
        //Variables utiles
        val botonGoogle: ImageButton = findViewById(R.id.bt_google)
        val botonLog: ImageButton = findViewById(R.id.bt_login)
        val userName: EditText = findViewById(R.id.textEdit_user)
        val userPass: EditText = findViewById(R.id.textEdit_pass)

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.serverId)).requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        findViewById<ImageButton>(R.id.bt_google).setOnClickListener {
            signInGoogle()
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun signInGoogle() {
        val singInIntent = googleSignInClient.signInIntent
        launcher.launch(singInIntent)
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount = task.result
            if (account != null)
                updateUI(account)
        } else
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent: Intent = Intent(this, UserPage::class.java)
                intent.putExtra("naame", account.displayName)
                startActivity(intent)
            } else
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}

