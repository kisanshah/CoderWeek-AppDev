package com.example.mapandfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main_login_page.*

class MainLoginPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = auth.currentUser
        if (user != null) {
            if (user.isEmailVerified)
                startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login_page)
        auth = FirebaseAuth.getInstance()
        googleSignIn.setOnClickListener {
            Toast.makeText(this, "Current not Available", Toast.LENGTH_LONG).show()
        }
        emailSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
