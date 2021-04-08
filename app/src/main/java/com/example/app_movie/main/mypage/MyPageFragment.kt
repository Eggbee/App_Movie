package com.example.app_movie.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.LoginActivity
import com.example.app_movie.databinding.FragmentMyPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInsrtanceState: Bundle?
    ): View? {
        if (_binding == null) _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val firebaseAuth = FirebaseAuth.getInstance()
        val adapter = MyPageAdapter(arrayListOf())
        binding?.recyclerFavorite?.adapter = adapter
        binding?.btLogout?.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
        return binding?.root
    }
}
