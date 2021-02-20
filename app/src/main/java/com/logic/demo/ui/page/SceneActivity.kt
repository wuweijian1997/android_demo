package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.databinding.ActivitySceneBinding

class SceneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySceneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySceneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}