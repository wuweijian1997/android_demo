package com.logic.demo.learn.android.demo.video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)
        videoPlay.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
        videoPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }
        videoReplay.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}