package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.databinding.ActivitySurfaceViewBinding

class SurfaceViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurfaceViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurfaceViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}