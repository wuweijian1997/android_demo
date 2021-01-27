package com.logic.demo.ui.page

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R
import com.logic.demo.databinding.ActivityPictureInPictureBinding

class PictureInPictureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureInPictureBinding
    private val videoReceiverAction = "media_control"
    @RequiresApi(Build.VERSION_CODES.O)
    private val mPictureInPictureParamsBuilder = PictureInPictureParams.Builder()
    private val videoReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(binding.video.isPlaying) {
                binding.video.pause()
            } else {
                binding.video.resume()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureInPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        binding.video.setVideoURI(uri)
        binding.video.start()
        binding.play.setOnClickListener {
            binding.video.resume()
        }
        binding.pause.setOnClickListener {
            binding.video.pause()
        }

        initVideoReceiver()
    }

    private fun initVideoReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(videoReceiverAction)
        registerReceiver(videoReceiver, intentFilter)
    }


    private fun minimize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //丛横比
            mPictureInPictureParamsBuilder.setAspectRatio(
                Rational(
                    2,
                    1
                )
            )
            val intent = PendingIntent.getBroadcast(
                this,
                0, Intent(videoReceiverAction)
                    .putExtra("control_type", 2), 0
            )
            mPictureInPictureParamsBuilder.setActions(
                listOf(
                    RemoteAction(
                        Icon.createWithResource(
                            this,
                            R.drawable.ic_pause_24dp
                        ),
                        "Pause",
                        "Pause",
                        intent
                    )
                )
            )
            enterPictureInPictureMode(mPictureInPictureParamsBuilder.build())
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        minimize()
    }

    override fun onStop() {
        super.onStop()
        binding.video.pause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("onBackPressed", "onBackPressed")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.video.suspend()
    }
}