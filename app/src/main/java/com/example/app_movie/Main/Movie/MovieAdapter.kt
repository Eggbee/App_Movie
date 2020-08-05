package com.example.app_movie.Main.Movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MovieAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(i: Int): Fragment? {
        when (i) {
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }
}