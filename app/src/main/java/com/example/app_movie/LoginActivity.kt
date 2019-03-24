package com.example.app_movie

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.app_movie.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity(), LoginNavigator {
    override fun intentSign() {
        startActivity(Intent(this, SignActivity::class.java))
        finish()
    }

    override fun intentMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun intentSignin() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.login = LoginViewModel(this)
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            intentMain()
        }
        var num = 0;
        val viewPager = findViewById<ViewPager>(R.id.view_login)
        viewPager.adapter = LoginAdapter(supportFragmentManager)
        viewPager.currentItem = 0
        val t = object : Thread() {
            override fun run() {
                while (true) {
                    try {
                        Thread.sleep(3000)
                        runOnUiThread {
                            if (num == 2) {
                                num = 0
                                viewPager.currentItem = num
                            } else {
                                num = num + 1
                                viewPager.currentItem = num
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        }
        t.start()
    }
}
