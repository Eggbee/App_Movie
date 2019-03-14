package com.example.app_movie

import android.arch.lifecycle.ViewModel
import android.view.View

class LoginViewModel(val loginNavigator: LoginNavigator): ViewModel() {
    fun touch(view: View){
        loginNavigator.intentSignin()
    }
    fun touch_login(view:View){
        loginNavigator.intentMain()
    }
}