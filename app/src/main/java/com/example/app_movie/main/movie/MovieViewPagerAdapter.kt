package com.example.app_movie.main.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movie.databinding.ItemPosterBinding

class MovieViewPagerAdapter(
    private val item: ArrayList<Triple<Int, String, String>>
) : RecyclerView.Adapter<MovieViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view =
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE    // 아이템의 갯수를 임의로 확 늘린다.

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item[position % 2])
    }

    inner class PagerViewHolder(val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Triple<Int,String,String>){
            binding.icPoster.setImageResource(value.first)
            binding.textPosterTitle.text = value.second
            binding.textPosterContent.text = value.third
        }
    }

}