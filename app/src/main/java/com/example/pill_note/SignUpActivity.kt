package com.example.pill_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pill_note.databinding.ActivityAuthBinding
import com.example.pill_note.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth
        binding.signUpButton.setOnClickListener(){
            Log.d("pill_note", "sign up button clicked")
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            createAccount(email, password)
        }

    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("pill_note", "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w("pill_note", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }
}