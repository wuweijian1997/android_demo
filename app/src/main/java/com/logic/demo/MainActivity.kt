package com.logic.demo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.logic.demo.learn.android.database.MyDatabaseHelper
import com.logic.demo.learn.android.demo.audio.AudioActivity
import com.logic.demo.learn.android.demo.broadcast.LoginActivity
import com.logic.demo.learn.android.demo.camera.CameraActivity
import com.logic.demo.learn.android.demo.contacts.ContactsActivity
import com.logic.demo.learn.android.fragment.FragmentDemo
import com.logic.demo.learn.android.layout.ListViewDemo
import com.logic.demo.learn.android.layout.RecyclerViewDemo
import com.logic.demo.learn.android.layout.StaggeredGridDemo
import com.logic.demo.learn.android.demo.news.ActivityNews
import com.logic.demo.learn.android.demo.notification.NotificationActivity
import com.logic.demo.learn.android.demo.runtime.permission.PermissionActivity
import com.logic.demo.learn.android.demo.thread.ThreadActivity
import com.logic.demo.learn.android.demo.video.VideoActivity
import com.logic.demo.learn.android.receiver.TimeChangeReceiver
import com.logic.demo.learn.lifecycle.DialogActivity
import com.logic.demo.learn.lifecycle.NormalActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    lateinit var timeChangeReceiver: TimeChangeReceiver
    private val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_main)
        initReceiver()
        onSave("Hello World")
        if (savedInstanceState != null) {
            ///恢复数据
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, tempData)
        }
        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        startListView.setOnClickListener {
            val intent = Intent(this, ListViewDemo::class.java)
            startActivity(intent)
        }
        startRecyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerViewDemo::class.java)
            startActivity(intent)
        }
        startStaggeredGrid.setOnClickListener {
            val intent = Intent(this, StaggeredGridDemo::class.java)
            startActivity(intent)
        }
        startFragment.setOnClickListener {
            val intent = Intent(this, FragmentDemo::class.java)
            startActivity(intent)
        }
        startNews.setOnClickListener {
            val intent = Intent(this, ActivityNews::class.java)
            startActivity(intent)
        }
        myBroadcastReceiver.setOnClickListener {
            val intent = Intent("com.logic.demo.MY_BROADCAST")
            intent.setPackage(packageName)
            //发送标准广播
//            sendBroadcast(intent)
            /// 发送有序广播
            sendOrderedBroadcast(intent, null)
        }
        broadcastOffline.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        permission.setOnClickListener {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
        getContacts.setOnClickListener {
            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
        }
        toNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        toTakePhoto.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        toAudio.setOnClickListener {
            val intent = Intent(this, AudioActivity::class.java)
            startActivity(intent)
        }
        toVideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }

        toThread.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)
        }


    }

    private fun onSave(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine { content.append(it) }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return content.toString()
    }

    private fun onSaveSharedPreferences() {
        val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
        editor.putString("name", "Tom")
        editor.putInt("age", 28)
        editor.putBoolean("married", false)
        editor.apply()
    }

    private fun onLoadSharedPreferences() {
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        prefs.getString("name", "")
        prefs.getInt("age", 0)
        prefs.getBoolean("married", false)
    }

    /// 初始化广播
    private fun initReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    ///这个方法在Activity被回收之前保证被调用.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }
}