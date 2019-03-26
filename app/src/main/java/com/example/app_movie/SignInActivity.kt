package com.example.app_movie

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.app_movie.Main.MainActivity
import com.example.app_movie.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity(), SigninNavigator {
    lateinit var edit_email: EditText
    lateinit var edit_password: EditText
    lateinit var edit_name:EditText

    fun result(email: String, password: String) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase:FirebaseDatabase= FirebaseDatabase.getInstance()
        val database: DatabaseReference =firebaseDatabase.getReference()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val signinData= SigninData()
                    signinData.name=edit_name.text.toString()
                    database.child(firebaseAuth.uid.toString()).push().setValue(signinData)
                    Toast.makeText(this, "성공함~~", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else if (password.length < 7) {
                    Toast.makeText(this, "비밀번호는 6자리 이상임~", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "이메일이 중복됬어요~", Toast.LENGTH_SHORT).show()
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
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this,
            R.layout.activity_sign_in
        )
        binding.sign= SigninViewModel(this)
        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)
        edit_name=findViewById(R.id.edit_name)
    }
}
