package com.example.app_movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MyPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInsrtanceState: Bundle?): View? {
        val layout = inflater.inflate(com.example.app_movie.R.layout.fragment_my_page, container, false) as ViewGroup
        val bt_logout = layout.findViewById<Button>(com.example.app_movie.R.id.bt_logout)
        bt_logout.setOnClickListener {
            val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            Toast.makeText(context,"로그아웃됨",Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
        return layout
    }
}