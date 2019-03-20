package com.example.app_movie

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.app_movie.databinding.ActivityLoginBinding
import com.example.app_movie.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(),SigninNavigator {
    override fun intentMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun intentLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
        binding.sign= SigninViewModel(this)
    }
}
