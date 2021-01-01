package com.logic.demo.learn.android.demo.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.logic.demo.R
import kotlinx.android.synthetic.main.activity_thread.*
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {
    val updateText = 1
    val handle = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> threadText.text = "Nice to meet you"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handle.sendMessage(msg)
            }
        }

    }
}