package com.example.app_movie.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_movie.R
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null
    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }

    private fun initializePlayer() {
        if (player == null) {
            player = SimpleExoPlayer.Builder(this).build()
            playerView.player = player
            playerView.controllerAutoShow = true
            val defaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory(getString(R.string.app_name))
            val mediaSource = ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)
                .createMediaSource(VideoModel.MP4_URI)
            player!!.prepare(mediaSource)
            player!!.seekTo(currentWindow, playbackPosition)
            player!!.playWhenReady = playWhenReady
        }
    }

    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }


    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onRestart() {
        super.onRestart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
}
