package com.logic.demo.learn.android.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.logic.demo.R
import com.logic.demo.learn.android.demo.service.ServiceActivity

class MyService : Service() {
    private val mBinder = DownloadBinder()

    class DownloadBinder: Binder() {
        fun startDownload() {
            Log.d("MyService", "StartDownload executed")
        }
        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    // Service创建的时候调用
    override fun onCreate() {
        Log.d("MyService", "onCreate")
        super.onCreate()
        //前台Service,会在通知栏显示一个通知,无法常规关闭.
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0 , intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.rem)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.rem))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    // 在每次service启动的时候调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    // 销毁的时候调用
    override fun onDestroy() {
        Log.d("MyService", "onDestroy")
        super.onDestroy()
    }
}