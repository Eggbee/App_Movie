package com.example.app_movie.Main.Movie

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.app_movie.LoginAdapter
import com.example.app_movie.R
import com.example.app_movie.Search.SearchActivity

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_movie, container, false) as ViewGroup
        val viewPager = layout.findViewById<ViewPager>(R.id.view_movie)
        viewPager.adapter = MovieAdapter(childFragmentManager)
        return layout
    }
}