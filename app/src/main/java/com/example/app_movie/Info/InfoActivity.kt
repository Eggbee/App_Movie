package com.example.app_movie.Info

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.app_movie.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val ic_movie=findViewById<ImageView>(R.id.ic_movie)
        var text_title=findViewById<TextView>(R.id.text_title)
        val intent=getIntent()
        val image=intent.extras.getString("image")
        text_title.text=intent.extras.getString("title")
        Glide.with(this).load(image).into(ic_movie)
    }
}