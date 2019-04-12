package com.example.app_movie.Main.Recommend

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app_movie.R

import java.util.ArrayList

class RecommendAdapter(internal var context: Context, internal var recommendModel: ArrayList<RecommendModel>) :
    RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recommend, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(recommendModel[i])
    }

    override fun getItemCount(): Int {
        return recommendModel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_title = itemView.findViewById<TextView>(R.id.text_title)
        val text_year = itemView.findViewById<TextView>(R.id.text_year)
        val ic_movie = itemView.findViewById<ImageView>(R.id.ic_movie)
        fun bind(recommendModel: RecommendModel) {
            text_title?.text = recommendModel.text_title
            text_year?.text = recommendModel.text_year
            if (ic_movie != null) {
                Glide.with(itemView).load(recommendModel.ic_image).apply(RequestOptions().override(150, 175))
                    .into(ic_movie)
            }
        }
    }
}
