package com.example.app_movie

import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.app_movie.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity(), SigninNavigator {
    lateinit var edit_email: EditText
    lateinit var edit_password: EditText

    fun result(email:String, password:String) {
        val firebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"등록됨~~",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "등록 에러!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun intentMain() {
        result(edit_email.text.toString().trim(),edit_password.text.toString().trim())
    }

    override fun intentLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
        binding.sign = SigninViewModel(this)
        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)
    }
}
