package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.R
import com.logic.demo.databinding.ActivityViewBindingBinding
import com.logic.demo.learn.android.extension.showToast

class ViewBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewBindingText.setOnClickListener {
            "点击文字".showToast()
        }
        binding.viewBindingImage.setOnClickListener {
            "点击图片".showToast()
        }
    }
}