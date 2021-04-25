package com.example.app_movie.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.category.CategoryModel
import com.example.app_movie.databinding.ItemMovieBinding
import com.example.app_movie.databinding.ItemPopularMovieBinding
import com.example.app_movie.databinding.ItemPopularMovieInfoBinding
import com.example.app_movie.main.model.MovieModel
import com.example.app_movie.util.ClickEvent
import java.util.ArrayList

class MovieAdapter(
    private var movieModel: ArrayList<Pair<String,ArrayList<MovieModel>>>,
    private val clickEvent: ClickEvent<Pair<ItemPopularMovieInfoBinding,MovieModel>>
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(),ClickEvent<Pair<ItemPopularMovieInfoBinding,MovieModel>> {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemPopularMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(movieModel[i])
    }

    override fun getItemCount(): Int = movieModel.size

    inner class ViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Pair<String,ArrayList<MovieModel>>) {
            binding.textMovieTitle.text = value.first
            binding.viewMovie.adapter = MovieInfoAdapter(value.second,this@MovieAdapter)
        }
    }

    override fun onClick(value: Pair<ItemPopularMovieInfoBinding, MovieModel>) {
        clickEvent.onClick(value)
    }
}