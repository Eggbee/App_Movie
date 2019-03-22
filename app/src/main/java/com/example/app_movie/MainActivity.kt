package com.example.app_movie

import android.app.FragmentManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Movie
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

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
