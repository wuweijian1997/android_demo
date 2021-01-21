package com.logic.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.logic.demo.const.homePageList
import com.logic.demo.databinding.ActivityMainBinding
import com.logic.demo.ui.adapter.HomeGridAdapter

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HomeGridAdapter(homePageList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.homePageList.layoutManager = layoutManager
        binding.homePageList.adapter = adapter
    }
}