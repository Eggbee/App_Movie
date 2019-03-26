package com.example.app_movie

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View

class SigninViewModel(val signinNavigator: SigninNavigator): ViewModel() {
    val email=ObservableField<String>("")
    val password=ObservableField<String>("")
    fun touch(view: View){
        signinNavigator.intentLogin()

    }
    fun touch_main(view:View){
        signinNavigator.intentMain()
    }
}