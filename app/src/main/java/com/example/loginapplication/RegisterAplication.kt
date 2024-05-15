package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.example.loginapplication.databinding.ActivityMainBinding
import com.example.loginapplication.databinding.ActivityRegisterAplicationBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterAplication : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding: ActivityRegisterAplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_aplication)


        binding = ActivityRegisterAplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerbtn.setOnClickListener {
            val email: String = binding.emailInput.text.toString().trim()
            val password: String = binding.passwordInput.text.toString().trim()
            val confirmPassword: String = binding.confirmPasswordInput.text.toString().trim()

            if (email.isEmpty()) {
                binding.emailInput.error = "Input Email"
                binding.emailInput.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailInput.error = "Iil"
                binding.emailInput.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.passwordInput.error = "password be more tthan 6 characters"
                binding.passwordInput.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.passwordInput.error = "password mus be match"
                binding.passwordInput.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }

        binding.log.setOnClickListener {
            val intenlog = Intent(this, MainActivity::class.java)
            startActivity(intenlog)
        }

        }

    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
//    override fun onStart() {
//        super.onStart()
//        if (firebaseAuth.currentUser != null) {
//            Intent(this, Home::class.java).also {
//                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(it)
//            }
//            }
//        }
}
