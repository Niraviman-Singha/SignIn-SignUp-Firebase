package com.example.liginregistrationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.liginregistrationfirebase.databinding.ActivityHomeBinding
import com.example.liginregistrationfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            finish()
        }
    }
}