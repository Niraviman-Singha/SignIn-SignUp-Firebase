package com.example.liginregistrationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.liginregistrationfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.exchange.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        binding.registerBtn.setOnClickListener {
            if (binding.emailET.text.toString() == "" && binding.passwordET.text.toString() ==""){

                Toast.makeText(this@MainActivity,"Please enter all the information",Toast.LENGTH_SHORT).show()
            }
            else{
                Firebase.auth.createUserWithEmailAndPassword(binding.emailET.text.toString(), binding.passwordET.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                       startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this@MainActivity,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null){
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
    }
}