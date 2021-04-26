package com.example.app_movie.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.app_movie.R
import com.example.app_movie.databinding.ActivityMainBinding
import com.example.app_movie.search.SearchActivity
import com.example.app_movie.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) setUpBottomNavigationBar()

        binding.icSearch.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setUpBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_movie,
            R.navigation.navigation_search,
            R.navigation.navigation_recommend,
            R.navigation.navigation_mypage
        )

        binding.bottomMain.setupWithNavController(
            navGraphIds,
            supportFragmentManager,
            R.id.frag_main,
            intent
        )
    }
}
