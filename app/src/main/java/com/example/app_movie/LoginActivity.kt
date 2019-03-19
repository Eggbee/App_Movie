package com.example.app_movie

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.app_movie.databinding.ActivityLoginBinding
import java.lang.Exception
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity(), LoginNavigator {
    override fun intentMain() {
        startActivity(Intent(this, SignInActivity::class.java))
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
