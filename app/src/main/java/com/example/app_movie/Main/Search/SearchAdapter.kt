package com.example.app_movie.Main.Search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app_movie.R

import java.util.ArrayList

class SearchAdapter(internal var context: Context, internal var searchModels: ArrayList<SearchModel>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    internal var recycler_item: Int = 0

    init {
        this.recycler_item = recycler_item
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val searchModel = searchModels[i]
        viewHolder.text_category.text = searchModel.text_categoty
    }

    override fun getItemCount(): Int {
        return searchModels.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_category: TextView
        init {
            text_category = itemView.findViewById(R.id.text_category)
        }
    }
}
