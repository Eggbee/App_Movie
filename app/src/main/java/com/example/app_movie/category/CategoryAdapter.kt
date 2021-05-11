package com.example.app_movie.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.databinding.ItemMovieBinding
import com.example.app_movie.databinding.ItemMovieSearchBinding
import com.example.app_movie.util.ClickEvent
import java.util.*

class CategoryAdapter(
    private var categoryModel: ArrayList<CategoryModel>,
    private val clickEvent: ClickEvent<Pair<CategoryModel, View>>
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(categoryModel[i])
    }

    override fun getItemCount(): Int = categoryModel.size

    inner class ViewHolder(private val binding: ItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: CategoryModel) {
            binding.textMovie.text = value.textMovie
            Glide.with(binding.root).load(value.textImage).error(R.drawable.icon)
                .into(binding.icMovie)
            binding.viewMovie.setOnClickListener {
                clickEvent.onClick(Pair(value, binding.root))
            }
        }
    }
}
