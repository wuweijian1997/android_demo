package com.logic.demo.learn.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.logic.demo.learn.android.extension.showToast

class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        "AnotherBroadcastReceiver".showToast()
    }
}