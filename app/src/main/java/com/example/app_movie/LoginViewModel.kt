package com.example.app_movie

import android.view.View
import androidx.lifecycle.ViewModel

class LoginViewModel(val loginNavigator: LoginNavigator) : ViewModel() {
    fun touch(view: View) {
        loginNavigator.intentSignin()
    }

    fun touch_sign(view: View) {
        loginNavigator.intentSign()
    }
}