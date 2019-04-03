package com.example.app_movie.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import com.example.app_movie.LoginActivity
import com.example.app_movie.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class MyPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInsrtanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_my_page, container, false) as ViewGroup
        val bt_logout = layout.findViewById<Button>(R.id.bt_logout)
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase:FirebaseDatabase= FirebaseDatabase.getInstance()
        bt_logout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(context,"로그아웃됨",Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
        return layout
    }
}
