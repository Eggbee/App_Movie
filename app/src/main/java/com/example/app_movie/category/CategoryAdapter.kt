package com.example.app_movie.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.databinding.ItemMovieBinding
import kotlinx.android.synthetic.main.item_movie.*
import java.util.*

class CategoryAdapter(private var categoryModel: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(categoryModel[i])
    }

    override fun getItemCount(): Int = categoryModel.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: CategoryModel) {
            binding.textMovie.text = value.textMovie
            Glide.with(binding.root).load(value.textImage).error(R.drawable.icon).into(binding.icMovie)
        }
    }
}
