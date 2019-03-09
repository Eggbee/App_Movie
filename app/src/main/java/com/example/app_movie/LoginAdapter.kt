package com.example.app_movie

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

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