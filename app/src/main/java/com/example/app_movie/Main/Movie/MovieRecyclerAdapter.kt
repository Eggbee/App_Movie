package com.example.app_movie.Main.Movie

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app_movie.R
import java.util.ArrayList

class MovieRecyclerAdapter(internal var context: Context, internal var movieModel: ArrayList<MovieModel>) :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(movieModel[i])
    }

    override fun getItemCount(): Int {
        return movieModel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_title = itemView.findViewById<TextView>(R.id.text_movie)
        val ic_movie = itemView.findViewById<ImageView>(R.id.ic_movie)
        fun bind(movieModel: MovieModel) {
            text_title?.text = movieModel.text_Title
            if (ic_movie != null) {
                Glide.with(itemView).load(movieModel.text_Image).apply(RequestOptions().override(150, 175))
                    .into(ic_movie)
            }
        }
    }
}

