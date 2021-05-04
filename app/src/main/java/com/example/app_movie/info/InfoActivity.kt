package com.example.app_movie.info

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app_movie.R
import com.example.app_movie.connect.MovieServiceUtil
import com.example.app_movie.databinding.ActivityInfoBinding
import com.example.app_movie.video.VideoActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class InfoActivity : AppCompatActivity() {

    private val infoViewBinding: ActivityInfoBinding by lazy {
        ActivityInfoBinding.inflate(layoutInflater)
    }
    private val movieId: Int by lazy {
        intent.getIntExtra("id", 0)
    }
    private val movieServiceUtil: MovieServiceUtil by inject()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(infoViewBinding.root)
        getMovieDetail()
    }

    private fun getMovieDetail() {
        movieServiceUtil.getMovieDetail(movieId)
            .zipWith(movieServiceUtil.getVideoInfo(movieId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Pair(it.first, it.second.results) }
            .subscribe({ value ->
                infoViewBinding.textTitle.text = value.first.title
                infoViewBinding.textOverview.text = value.first.overview
                Glide.with(infoViewBinding.root)
                    .load("https://image.tmdb.org/t/p/w342${value.first.posterPath}")
                    .error(R.drawable.icon)
                    .into(infoViewBinding.icMovie)
                infoViewBinding.viewVideo.setOnClickListener {
                    if(value.second?.size ?: 0 > 0){
                        startActivity(Intent(this, VideoActivity::class.java)
                            .putExtra("key", value.second?.get(0)?.get("key")?.asString))
                    }
                }
            }, {
            }).addTo(compositeDisposable)
    }


}