package com.logic.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.logic.demo.learn.android.layout.ListViewDemo
import com.logic.demo.learn.android.layout.RecyclerViewDemo
import com.logic.demo.learn.android.layout.StaggeredGridDemo
import com.logic.demo.learn.lifecycle.DialogActivity
import com.logic.demo.learn.lifecycle.NormalActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null) {
            ///恢复数据
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, tempData)
        }
        startNormalActivity.setOnClickListener{
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener{
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