package com.example.mapandfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        SignUpButton.setOnClickListener {
            if (checkInput()) {
                registerUser(email.text.toString(), pass1.text.toString())
            }
        }
    }

    private fun checkInput(): Boolean {

        return if (email.text.toString().isEmpty() || pass1.text.toString()
                .isEmpty() || pass2.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Empty Input", Toast.LENGTH_LONG).show()
            false
        } else if (pass1.text.toString() != pass2.text.toString()
        ) {
            Toast.makeText(this, "Password Doest Match", Toast.LENGTH_LONG).show()
            false
        } else if (pass1.length() < 6 || pass2.length() < 6) {
            Toast.makeText(this, "Password too short", Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                auth.currentUser?.sendEmailVerification()
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Registration Successful.\nNow you can Sign In",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this, SignInActivity::class.java))
                } else {
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}
