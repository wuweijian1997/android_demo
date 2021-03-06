package com.logic.demo.ui.page

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.setOnClickListener {
            ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100).setDuration(3000).start()
        }
    }
}