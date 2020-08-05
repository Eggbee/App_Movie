package com.example.app_movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LoginAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(i: Int): Fragment? {
        when (i) {
            0 -> return LoginFirstFragment()
            1 -> return LoginSecondFragment()
            2 -> return LoginThirdFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }
}