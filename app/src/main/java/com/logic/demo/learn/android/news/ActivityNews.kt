package com.logic.demo.learn.android.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R
import com.logic.demo.learn.android.SecondActivity
import com.logic.demo.learn.android.news.fragment.NewsContentFragment
import kotlinx.android.synthetic.main.activity_news_content.*

class ActivityNews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}