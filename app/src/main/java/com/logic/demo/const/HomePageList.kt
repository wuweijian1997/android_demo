package com.logic.demo.const

import androidx.appcompat.app.AppCompatActivity
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
import com.logic.demo.ui.page.*

val homePageList = listOf<PageItem<AppCompatActivity>>(
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
    PageItem("ViewBinding", ViewBindingActivity::class.java),
    PageItem("Intent", IntentActivity::class.java),
    PageItem("快捷方式", ShortcutActivity::class.java),
    PageItem("DataBinding", DataBindingActivity::class.java),
    PageItem("DataStore", DataStoreActivity::class.java),
    PageItem("LiveData", LiveDataActivity::class.java),
    PageItem("MotionLayout", MotionLayoutActivity::class.java),
    PageItem("DragDrop", DragDropActivity::class.java),
    PageItem("PictureInPicture", PictureInPictureActivity::class.java),
    PageItem("ConstraintLayout", ActivityConstraint::class.java),
    PageItem("LoadFragment", FragmentActivity::class.java)
)
