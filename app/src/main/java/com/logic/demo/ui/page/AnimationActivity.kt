package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import com.logic.demo.R
import com.logic.demo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewAlphaAnimation.setOnClickListener {
            val alphaXml: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
            val scaleClass = ScaleAnimation(0f, 1f, 1f, 2f)
            scaleClass.duration = 1000
            binding.viewAlphaAnimation.startAnimation(scaleClass)
        }

        binding.setAnimation.setOnClickListener {
            val set: Animation = AnimationUtils.loadAnimation(this, R.anim.set)
            binding.setAnimation.startAnimation(set)
        }
    }
}