package com.example.app_movie

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SigninViewModel(val signinNavigator: SigninNavigator) : ViewModel() {
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")
    fun touch(view: View) {
        signinNavigator.intentLogin()

    }

    fun touch_main(view: View) {
        signinNavigator.intentMain()
    }
}