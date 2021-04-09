package com.example.app_movie.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movie.databinding.ItemCategoryBinding
import com.example.app_movie.util.ClickEvent
import java.util.*

class SearchAdapter(private val searchModel: List<String>,private val clickEvent: ClickEvent<Int>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemCategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(searchModel[i])
    }

    override fun getItemCount(): Int = searchModel.size

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            binding.textCategory.text = value
            binding.viewCategory.setOnClickListener { clickEvent.onClick(adapterPosition) }
        }
    }
}
