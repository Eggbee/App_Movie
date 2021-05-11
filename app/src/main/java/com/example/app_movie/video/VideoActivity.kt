package com.example.app_movie.video

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.app_movie.R
import com.example.app_movie.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class VideoActivity : AppCompatActivity() {

    private val videoBinding: ActivityVideoBinding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    private val videoKey: String by lazy {
        intent.getStringExtra("key") ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(videoBinding.root)
    }

    @SuppressLint("StaticFieldLeak")
    private fun initializePlayer() {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                vMeta: VideoMeta
            ) {
                if (ytFiles != null) {
                    val player = SimpleExoPlayer.Builder(this@VideoActivity).build()
                    videoBinding.playerView.player = player
                    videoBinding.playerView.controllerAutoShow = true

                    val mediaItem = MediaItem.fromUri(Uri.parse(ytFiles[22].url))
                    val userAgent =
                        Util.getUserAgent(this@VideoActivity, getString(R.string.app_name))
                    val factory = DefaultDataSourceFactory(this@VideoActivity, userAgent)
                    val progressiveMediaSource =
                        ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem)

                    player.setMediaSource(progressiveMediaSource)
                    player.prepare()
                    player.play()
                }
            }
        }.extract("http://youtube.com/watch?v=$videoKey", true, true)
    }

    private fun releasePlayer() {
//        playbackPosition = player.currentPosition
//        currentWindow = player.currentWindowIndex
//        playWhenReady = player.playWhenReady
//        player.release()
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
