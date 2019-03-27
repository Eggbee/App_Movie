package com.example.app_movie.Main.Movie

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

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