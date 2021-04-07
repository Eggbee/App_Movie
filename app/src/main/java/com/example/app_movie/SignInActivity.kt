package com.example.app_movie

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.app_movie.main.MainActivity
import com.example.app_movie.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity(), SigninNavigator {
    lateinit var edit_email: EditText
    lateinit var edit_password: EditText
    lateinit var edit_name: EditText

    fun result(email: String, password: String) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        val database: DatabaseReference = firebaseDatabase.getReference()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val signinData = SigninData()
                    signinData.name = edit_name.text.toString()
                    database.child(firebaseAuth.uid.toString()).push().setValue(signinData)
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else if (password.length < 7) {

                } else {
                }
            }
    }

    override fun intentMain() {
        result(edit_email.text.toString().trim(), edit_password.text.toString().trim())
    }

    override fun intentLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(
            this,
            R.layout.activity_sign_in
        )
        binding.sign = SigninViewModel(this)
        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)
        edit_name = findViewById(R.id.edit_name)
    }
}
