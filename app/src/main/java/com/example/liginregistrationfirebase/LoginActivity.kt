package com.example.liginregistrationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.liginregistrationfirebase.databinding.ActivityHomeBinding
import com.example.liginregistrationfirebase.databinding.ActivityLoginBinding
import com.example.liginregistrationfirebase.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mGoogleApiClient:GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.defailt_web_client_id)).requestEmail().build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this){
                    connectionResult->
                Toast.makeText(this@LoginActivity,"Google Service Error",Toast.LENGTH_SHORT).show()
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
            .build()

        binding.loginBtn.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            signInWithEmailAndPassword(email, password)
        }

    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                    task->
                if(task.isSuccessful){
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this@LoginActivity,"Something Wrong",Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }

    }
