package com.example.app_movie.Info

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.Video.VideoActivity

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val ic_movie=findViewById<ImageView>(R.id.ic_movie)
        val text_title=findViewById<TextView>(R.id.text_title)
        val ic_video=findViewById<ImageView>(R.id.ic_video)
        val intent=getIntent()
        val image=intent.extras.getString("image")
        text_title.text=intent.extras.getString("title")
        Glide.with(this).load(image).into(ic_movie)
        ic_video.setOnClickListener { startActivity(Intent(this,VideoActivity::class.java)) }
    }
}