package com.example.app_movie.main.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.category.CategoryActivity
import com.example.app_movie.databinding.FragmentSearchBinding
import com.example.app_movie.util.ClickEvent

class SearchFragment : Fragment(), ClickEvent<Int> {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    private val searchModel = listOf("드라마", "판타지", "서부", "공포", "로맨스", "스릴러")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInsrtanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = FragmentSearchBinding.inflate(inflater, container, false)
            binding?.recyclerCategory?.adapter = SearchAdapter(searchModel,this)
        }

        return binding?.root
    }

    override fun onClick(value: Int) {
        startActivity(Intent(context,CategoryActivity::class.java).putExtra("position",value))
    }
}
