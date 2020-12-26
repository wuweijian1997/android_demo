package com.logic.demo.learn.android.demo.news.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.logic.demo.R
import kotlinx.android.synthetic.main.news_content_flag.*

class NewsContentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_flag, container, false)
    }

    fun refresh(title: String, content: String) {
        contentLayout.visibility = View.VISIBLE
        //刷新新闻的标题
        newTitle.text = title
        //刷新新闻的内容
        newsContent.text = content
    }
}