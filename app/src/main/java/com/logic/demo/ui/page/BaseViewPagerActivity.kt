package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.logic.demo.R
import com.logic.demo.databinding.ActivityViewPagerBinding
import com.logic.demo.ui.adapter.ViewPageAdapter
import kotlin.math.abs

open class BaseViewPagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPagerBinding

    private val mAnimator = ViewPager2.PageTransformer { page: View, position: Float ->
        val absPos = abs(position)
        page.apply {
            val value = 1 - absPos
            alpha = value
            scaleX = value
            scaleY = value
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.viewPager.adapter = ViewPageAdapter()
//        binding.viewPager.setPageTransformer(mAnimator)
    }
}