package com.example.app_movie.Main

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.ImageView
import com.example.app_movie.Main.Movie.MovieFragment
import com.example.app_movie.Main.Recommend.RecommendFragment
import com.example.app_movie.Main.Search.SearchFragment
import com.example.app_movie.R
import com.example.app_movie.Search.SearchActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_main)
        val ic_Search = findViewById<ImageView>(R.id.ic_search)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frag_main, MovieFragment()).commitAllowingStateLoss()
        ic_Search.setOnClickListener {
            startActivity(Intent(applicationContext, SearchActivity::class.java))
            finish()
        }
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
