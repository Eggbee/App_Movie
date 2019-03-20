package com.example.app_movie

import android.arch.lifecycle.ViewModel
import android.view.View

class SigninViewModel(val signinNavigator: SigninNavigator): ViewModel() {
    fun touch(view: View){
        signinNavigator.intentLogin()
    }
    fun touch_main(view:View){
        signinNavigator.intentMain()
    }
}