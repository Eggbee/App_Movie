package com.example.app_movie.Search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.RequestOptions
import com.example.app_movie.R

import java.util.ArrayList

class SearchAdapter(internal var context: Context, internal var searchModel2s: ArrayList<com.example.app_movie.Search.SearchModel2>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(searchModel2s[i])
    }

    override fun getItemCount(): Int {
        return searchModel2s.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_movie=itemView.findViewById<TextView>(R.id.text_movie)
        var ic_movie=itemView.findViewById<ImageView>(R.id.ic_movie)
        fun bind(searchmodel: SearchModel2) {
            text_movie?.text=searchmodel.text_Movie
            if(ic_movie!=null){
                Glide.with(itemView).load(searchmodel.text_Image).apply(RequestOptions().override(150, 175)).into(ic_movie)
            }
        }
    }
}
