package com.example.pill_note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pill_note.databinding.ActivityAuthBinding
import com.example.pill_note.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signUpButton.setOnClickListener() {
            Log.d("pill_note", "sign up button clicked")
            val nickname = binding.nicknameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirm = binding.confirmPasswordEditText.text.toString()
            if (passwordConfirm == password) createAccount(email, password, nickname)
            else Toast.makeText(
                baseContext, "비밀번호가 일치하지 않습니다.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun createAccount(email: String, password: String, nickname: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("pill_note", "createUserWithEmail:success")
                    val user = auth.currentUser
                    writeNewUser(user!!.uid, nickname, user.email.toString())
                    user.updateProfile(userProfileChangeRequest {
                        displayName = nickname
                    }).addOnSuccessListener {
                        Log.d("pill_note", "User profile updated.")

                        // go to main activity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    Log.d("pill_note", "current user: ${user?.email}")
                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("pill_note", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        // [END create_user_with_email]
    }

    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(userId, email, name)
        db.getReference("users").child(userId).setValue(user)
        writeFollowInfo(userId, user)
    }

    private fun writeFollowInfo (userId: String, currentUser: User) {
        val followInfo = FollowInfo(userId, currentUser)
        db.getReference("follow").child(userId).setValue(followInfo)
    }
}