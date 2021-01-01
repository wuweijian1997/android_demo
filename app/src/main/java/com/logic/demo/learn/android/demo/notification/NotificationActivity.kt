package com.logic.demo.learn.android.demo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.logic.demo.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //手动关闭通知
        manager.cancel(1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
            val channel2 =
                NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel2)
        }
        createImportantNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setContentText("This is content text.")
                .setSmallIcon(R.drawable.rem)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.rem))
                .setContentIntent(pi)
                //点击后自动关闭通知
                .setAutoCancel(true)
                .build()
            manager.notify(2, notification)
        }
        createNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("This is content title")
                .setContentText("This is content text.")
//                .setStyle(NotificationCompat.BigTextStyle().bigText("This is content text.This is content text.This is content text.This is content text.This is content text.This is content text.This is content text.This is content text.This is content text."))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(resources, R.drawable.rem)
                ))
                .setSmallIcon(R.drawable.rem)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.rem))
                .setContentIntent(pi)
                //点击后自动关闭通知
//                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }
}