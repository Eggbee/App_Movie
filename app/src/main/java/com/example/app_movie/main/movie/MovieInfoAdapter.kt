package com.example.app_movie.main.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.databinding.ItemPopularMovieInfoBinding
import com.example.app_movie.main.model.MovieModel
import com.example.app_movie.util.ClickEvent
import java.util.*

class MovieInfoAdapter(
    private var movieModel: ArrayList<MovieModel>,
    private val clickEvent: ClickEvent<Pair<ItemPopularMovieInfoBinding, MovieModel>>
) :
    RecyclerView.Adapter<MovieInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemPopularMovieInfoBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(movieModel[i])
    }

    override fun getItemCount(): Int = movieModel.size

    inner class ViewHolder(private val binding: ItemPopularMovieInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: MovieModel) {
            binding.textMovie.text = value.title
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w342${value.posterPath}")
                .error(R.drawable.icon)
                .into(binding.icMovie)
            binding.viewMovieInfo.setOnClickListener { clickEvent.onClick(Pair(binding, value)) }
        }
    }
}