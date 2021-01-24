package com.logic.demo.ui.page

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.createDataStore
import com.logic.demo.R
import com.logic.demo.databinding.ActivityIntentBinding
import com.logic.demo.learn.android.extension.showToast
import com.logic.demo.model.CardItem
import com.logic.demo.util.ColorUtil
import java.util.*

class IntentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentBinding
    private val shareResult = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentLayout = binding.intentLayout
        for (i in cardList.indices) {
            val cardItem = cardList[i]
            val view =
                LayoutInflater.from(this).inflate(R.layout.card_item, intentLayout, false)
            val cardTitle = view.findViewById<TextView>(R.id.cardTitle)
            cardTitle.text = cardItem.title
            cardTitle.setOnClickListener { cardItem.onClick() }
            view.setBackgroundColor(ColorUtil.randomColor())
            intentLayout.addView(view)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            shareResult -> if (resultCode == Activity.RESULT_OK) {
                val result = data?.getBooleanExtra("result", false)
                if (result == true) {
                    "分享成功".showToast()
                } else {
                    "分享失败".showToast()
                }
            }
        }
    }

    private val cardList = listOf<CardItem>(
        CardItem("打电话") {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"))
            startActivity(callIntent)
        },
        CardItem("地图") {
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
            ).let { location ->
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent)

        },
        CardItem("网页") {
            val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.juejin.cn"))
            startActivity(webIntent)
        },
        CardItem("邮箱") {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("jon@example.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
                // You can also attach multiple items by passing an ArrayList of Uris
            }
            startActivity(emailIntent)
        },
        CardItem("日历") {
            val calendarIntent =
                Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
                    val beginTime: Calendar = Calendar.getInstance().apply {
                        set(2012, 0, 19, 7, 30)
                    }
                    val endTime = Calendar.getInstance().apply {
                        set(2012, 0, 19, 10, 30)
                    }
                    putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                    putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                    putExtra(CalendarContract.Events.TITLE, "Ninja class")
                    putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo")
                }
            startActivity(calendarIntent)
        },
        CardItem("验证是否存在可接收Intent的应用") {
            val textIntent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Email message text")
            }
            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(
                textIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            activities.forEach {
                Log.d("Activity", it.activityInfo.name)
                val view =
                    LayoutInflater.from(this)
                        .inflate(R.layout.card_item, binding.activitiesLayout, false)
                view.findViewById<TextView>(R.id.cardTitle).text =
                    it.activityInfo.name.split(".").last()
                view.setBackgroundColor(ColorUtil.randomColor())
                binding.activitiesLayout.addView(view)
            }
            val isIntentSafe: Boolean = activities.isNotEmpty()
            Log.d("isIntentSafe", isIntentSafe.toString())
        },
        CardItem("显示Intent选择器") {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
            }
            val chooser = Intent.createChooser(intent, "Hello World")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        },
        CardItem("分享页") {
            val intent = Intent(Intent.ACTION_SEND).apply {
                addCategory("com.logic.demo")
                putExtra("title", "这是分享的标题")
                putExtra(
                    "img",
                    "https://img.zcool.cn/community/01f68d5c0d11d9a80121ab5de16b86.jpg@1280w_1l_2o_100sh.jpg"
                )
            }
            startActivityForResult(intent, shareResult)
        }
    )


}