package com.example.app_movie.Main

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.app_movie.Main.Movie.MovieFragment
import com.example.app_movie.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frag_main, MovieFragment()).commitAllowingStateLoss()

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.action_one -> {
                    transaction.replace(R.id.frag_main, MovieFragment()).commitAllowingStateLoss()
                }
                R.id.action_two -> {
                    transaction.replace(R.id.frag_main, SearchFragment()).commitAllowingStateLoss()
                }
                R.id.action_three -> {
                    transaction.replace(R.id.frag_main, RecommendFragment()).commitAllowingStateLoss()
                }
                R.id.action_four -> {
                    transaction.replace(R.id.frag_main, MyPageFragment()).commitAllowingStateLoss()
                }
            }
            true
        }
    }
}
