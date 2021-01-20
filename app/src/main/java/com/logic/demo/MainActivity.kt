package com.logic.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.logic.demo.databinding.ActivityMainBinding
import com.logic.demo.learn.android.demo.audio.AudioActivity
import com.logic.demo.learn.android.demo.camera.CameraActivity
import com.logic.demo.learn.android.demo.contacts.ContactsActivity
import com.logic.demo.learn.android.demo.jetpack.JetpackActivity
import com.logic.demo.learn.android.demo.material.MaterialActivity
import com.logic.demo.learn.android.demo.news.ActivityNews
import com.logic.demo.learn.android.demo.notification.NotificationActivity
import com.logic.demo.learn.android.demo.runtime.permission.PermissionActivity
import com.logic.demo.learn.android.demo.service.ServiceActivity
import com.logic.demo.learn.android.demo.thread.ThreadActivity
import com.logic.demo.learn.android.demo.video.VideoActivity
import com.logic.demo.learn.android.demo.webview.WebviewActivity
import com.logic.demo.learn.android.layout.RecyclerViewDemo
import com.logic.demo.learn.android.layout.StaggeredGridDemo
import com.logic.demo.model.PageItem
import com.logic.demo.ui.adapter.HomeGridAdapter
import com.logic.demo.ui.page.ViewBindingActivity

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val pageList = listOf<PageItem<AppCompatActivity>>(
        PageItem("RecyclerView", RecyclerViewDemo::class.java),
        PageItem("StaggeredGrid", StaggeredGridDemo::class.java),
        PageItem("News", ActivityNews::class.java),
        PageItem("Permission", PermissionActivity::class.java),
        PageItem("Contacts", ContactsActivity::class.java),
        PageItem("Notification", NotificationActivity::class.java),
        PageItem("Camera", CameraActivity::class.java),
        PageItem("Audio", AudioActivity::class.java),
        PageItem("Video", VideoActivity::class.java),
        PageItem("Thread", ThreadActivity::class.java),
        PageItem("Service", ServiceActivity::class.java),
        PageItem("WebView", WebviewActivity::class.java),
        PageItem("Material", MaterialActivity::class.java),
        PageItem("ViewModel", JetpackActivity::class.java),
        PageItem("ViewBinding", ViewBindingActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HomeGridAdapter(pageList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.homePageList.layoutManager = layoutManager
        binding.homePageList.adapter = adapter
    }
}