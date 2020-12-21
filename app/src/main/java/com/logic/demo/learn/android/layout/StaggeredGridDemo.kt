package com.logic.demo.learn.android.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.logic.demo.R
import com.logic.demo.learn.android.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.recycler_view_demo.*

///瀑布流
class StaggeredGridDemo : AppCompatActivity() {
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

    private val fruitList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_demo)
        initFruits()
        val adapter = RecyclerViewAdapter(
            fruitList
        )
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(3) {
            data.forEach { fruitList.add(getRandomLengthString(it)) }
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}