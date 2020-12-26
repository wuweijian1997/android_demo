package com.logic.demo.learn.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        //阻止广播传递
        abortBroadcast()
    }
}