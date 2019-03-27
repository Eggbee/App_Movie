package com.example.app_movie.Main

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.app_movie.Main.Movie.MovieFragment
import com.example.app_movie.R

class MainActivity : AppCompatActivity() {
    internal var movieFragment = MovieFragment()
    internal var searchFragment = SearchFragment()
    internal var recommendFragment = RecommendFragment()
    internal var myPageFragment = MyPageFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_main)
        // 첫 화면 지정
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frag_main, movieFragment).commitAllowingStateLoss()

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.action_one -> {
                    transaction.replace(R.id.frag_main, movieFragment).commitAllowingStateLoss()
                }
                R.id.action_two -> {
                    transaction.replace(R.id.frag_main, searchFragment).commitAllowingStateLoss()
                }
                R.id.action_three -> {
                    transaction.replace(R.id.frag_main, recommendFragment).commitAllowingStateLoss()
                }
                R.id.action_four -> {
                    transaction.replace(R.id.frag_main, myPageFragment).commitAllowingStateLoss()
                }
            }
            true
        }
    }
}
