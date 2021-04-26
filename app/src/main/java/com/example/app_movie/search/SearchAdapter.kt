package com.example.app_movie.search

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.databinding.ItemMovieSearchBinding
import com.example.app_movie.main.model.MovieModel

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    val data = arrayListOf<MovieModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(data[i])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(value: ArrayList<MovieModel>){
        data.addAll(value)
        notifyDataSetChanged()
    }

    fun remove(){
        data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: MovieModel) {
            binding.textMovie.text = value.title
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w342${value.posterPath}")
                .error(R.drawable.icon)
                .into(binding.icMovie)
        }
    }
}
