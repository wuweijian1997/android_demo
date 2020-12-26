package com.logic.demo.learn.android.demo.broadcast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.logic.demo.R
import kotlinx.android.synthetic.main.activity_broadcast_main.*

class BroadcastMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_main)
        forceOffline.setOnClickListener {
            val intent = Intent("com.logic.demo.FORCE_OFFLINE")
            Log.i("Kafka", "BroadcastMainActivity")
            sendBroadcast(intent)
        }
    }
}