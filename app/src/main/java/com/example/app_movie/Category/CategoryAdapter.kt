package com.example.app_movie.Category

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

class CategoryAdapter(internal var context: Context, internal var categoryModel: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(categoryModel[i])
    }

    override fun getItemCount(): Int {
        return categoryModel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_movie = itemView.findViewById<TextView>(R.id.text_movie)
        var ic_movie = itemView.findViewById<ImageView>(R.id.ic_movie)
        fun bind(categoryModel1: CategoryModel) {
            text_movie?.text = categoryModel1.text_Movie
            if (ic_movie != null) {
                Glide.with(itemView).load(categoryModel1.text_Image).apply(RequestOptions().override(150, 175))
                    .into(ic_movie)
            }
        }
    }
}
