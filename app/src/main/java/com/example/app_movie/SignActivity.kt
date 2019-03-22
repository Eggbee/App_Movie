package com.example.app_movie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignActivity : AppCompatActivity() {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        val edit_email:EditText=findViewById(R.id.edit_email)
        val edit_password:EditText=findViewById(R.id.edit_password)
        val bt_login:Button=findViewById(R.id.bt_login)
        bt_login.setOnClickListener { login(edit_email.text.toString().trim(),edit_password.text.toString().trim()) }
    }
    fun login(email:String,password:String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"성공함~~", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "아이디 혹은 비밀번호 틀림~~", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
