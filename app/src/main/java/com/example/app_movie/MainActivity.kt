package com.example.app_movie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_video=findViewById<Button>(R.id.button_video)
        button_video.setOnClickListener { startActivity(Intent(this, VideoActivity::class.java)) }
    }
}
