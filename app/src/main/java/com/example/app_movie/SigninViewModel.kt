package com.example.app_movie

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

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