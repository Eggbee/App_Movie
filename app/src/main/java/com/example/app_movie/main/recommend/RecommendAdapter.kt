package com.example.app_movie.main.recommend

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_movie.databinding.ItemRecommendBinding
import java.util.*

class RecommendAdapter(
    private val recommendModel: ArrayList<RecommendModel>
) :
    RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            ItemRecommendBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(recommendModel[i])
    }

    override fun getItemCount(): Int {
        return recommendModel.size
    }

    inner class ViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendModel: RecommendModel) {
            binding.textTitle.text = Html.fromHtml(recommendModel.textTitle).toString()
            binding.textYear.text = recommendModel.textYear
            Glide.with(binding.root).load(recommendModel.icImage).into(binding.icMovie)
        }
    }
}
