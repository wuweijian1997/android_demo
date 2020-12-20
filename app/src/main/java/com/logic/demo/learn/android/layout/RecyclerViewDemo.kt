package com.logic.demo.learn.android.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.logic.demo.learn.android.adapter.FruitAdapter
import com.logic.demo.R
import com.logic.demo.learn.android.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.list_view_demo.*
import kotlinx.android.synthetic.main.recycler_view_demo.*

class RecyclerViewDemo : AppCompatActivity() {
    private val data = listOf<String>(
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango",
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_demo)
        val adapter = RecyclerViewAdapter(
            data
        )
        val layoutManager = LinearLayoutManager(this)
        ///横向滚动
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}