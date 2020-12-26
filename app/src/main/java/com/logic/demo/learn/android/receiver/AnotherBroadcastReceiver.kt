package com.logic.demo.learn.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "AnotherBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}