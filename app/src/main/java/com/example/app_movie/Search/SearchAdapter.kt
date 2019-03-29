package com.example.app_movie.Search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.app_movie.R

import java.util.ArrayList

class SearchAdapter(internal var context: Context, internal var searchModel2s: ArrayList<com.example.app_movie.Search.SearchModel2>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    internal var recycler_item: Int = 0

    init {
        this.recycler_item = recycler_item
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val searchModel = searchModel2s[i]
        viewHolder.text_movie.text = searchModel.text_movie
        viewHolder.ic_movie.setImageURI(searchModel.text_image)
        Glide.with(context).load(searchModel.text_image).into(viewHolder.ic_movie)
    }

    override fun getItemCount(): Int {
        return searchModel2s.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_movie: TextView
        var ic_movie:ImageView
        init {
            text_movie = itemView.findViewById(R.id.text_movie)
            ic_movie=itemView.findViewById(R.id.ic_movie)
        }
    }
}
