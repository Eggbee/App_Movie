package com.example.app_movie

import androidx.lifecycle.ViewModel
import android.view.View

class LoginViewModel(val loginNavigator: LoginNavigator) : ViewModel() {
    fun touch(view: View) {
        loginNavigator.intentSignin()
    }

    fun touch_sign(view: View) {
        loginNavigator.intentSign()
    }
}