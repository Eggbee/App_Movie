package com.example.app_movie.info

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app_movie.category.CategoryModel
import com.example.app_movie.databinding.ActivityInfoBinding
import com.example.app_movie.video.VideoActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    private val movieInfoModel: CategoryModel by lazy {
        Gson().fromJson(intent.getStringExtra("movieInfo"), CategoryModel::class.java)
    }

    private val infoViewBinding: ActivityInfoBinding by lazy {
        ActivityInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(infoViewBinding.root)
        infoViewBinding.textTitle.text = movieInfoModel.textMovie
        Glide.with(this).load(movieInfoModel.textImage).into(infoViewBinding.icMovie)
        ic_video.setOnClickListener { startActivity(Intent(this, VideoActivity::class.java)) }
    }
}