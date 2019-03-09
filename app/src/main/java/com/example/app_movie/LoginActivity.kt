package com.example.app_movie

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.app_movie.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity(), LoginNavigator {
    override fun intentMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.login= LoginViewModel(this)
        val viewPager=findViewById<ViewPager>(R.id.view_login)
        viewPager.adapter=LoginAdapter(supportFragmentManager)
        viewPager.currentItem=0
    }
}
