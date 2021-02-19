package com.logic.demo.ui.page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.logic.demo.R
import com.logic.demo.databinding.ActivityViewPagerBinding
import com.logic.demo.ui.adapter.ViewPageAdapter
import kotlin.math.abs

class ViewPagerActivity : BaseViewPagerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewPager.adapter = ViewPageAdapter()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Index: $position"
        }.attach()
    }

}