package com.example.mapandfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        SignInButton.setOnClickListener {
            if (checkInput()) {
                verifyUser(email.text.toString(), password.text.toString())
            }
        }
        SignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun verifyUser(email: String, password: String) {
        Toast.makeText(this, email + "\n" + password, Toast.LENGTH_LONG).show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {

                    Toast.makeText(
                        this, "Authentication failed." + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

    }

    private fun checkInput(): Boolean {

        return if (email.text.toString().isEmpty() || password.text.toString()
                .isEmpty()
        ) {
            Toast.makeText(this, "Empty Input", Toast.LENGTH_LONG).show()
            false
        } else if (password.length() < 6) {
            Toast.makeText(this, "Password too short", Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }
}
