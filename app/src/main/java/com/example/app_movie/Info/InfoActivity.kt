package com.example.app_movie.Info

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.Video.VideoActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val intent = getIntent()
        val image = intent.extras.getString("image")
        text_title.text = intent.extras.getString("title")
        var num:Int=0
        Glide.with(this).load(image).into(ic_movie)
        ic_video.setOnClickListener { startActivity(Intent(this, VideoActivity::class.java)) }
        ic_favorite.setOnClickListener {
            if (num == 1) {
                Toast.makeText(applicationContext, "두번 누르시면 안됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
                val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
                val database: DatabaseReference = firebaseDatabase.getReference()
                val infoData = InfoData()
                infoData.text_Image = image
                infoData.text_Title = intent.extras.getString("title")
                database.child(firebaseAuth.uid.toString()).child("like").push().setValue(infoData)
                num=1
            }
        }
    }
}