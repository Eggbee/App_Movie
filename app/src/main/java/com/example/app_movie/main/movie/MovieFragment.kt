package com.example.app_movie.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {

    private var _binidng: FragmentMovieBinding? = null
    private val binding get() = _binidng

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binidng == null) {
            _binidng = FragmentMovieBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }
}