package com.example.app_movie.main.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app_movie.R
import com.example.app_movie.info.InfoData

class MyPageAdapter(var infoData: ArrayList<InfoData>) :
    RecyclerView.Adapter<MyPageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_favorite, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(infoData[i])
    }

    override fun getItemCount(): Int {
        return infoData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_title = itemView.findViewById<TextView>(R.id.text_title)
        var ic_image = itemView.findViewById<ImageView>(R.id.ic_image)
        fun bind(infoDatas: InfoData) {
            text_title?.text = infoDatas.text_Title
            if (ic_image != null) {
                Glide.with(itemView).load(infoDatas.text_Image)
                    .apply(RequestOptions().override(150, 175))
                    .into(ic_image)
            }
        }
    }
}
