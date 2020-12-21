package com.logic.demo.learn.android.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.logic.demo.learn.android.adapter.FruitAdapter
import com.logic.demo.R
import kotlinx.android.synthetic.main.list_view_demo.*

class ListViewDemo : AppCompatActivity() {
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
        setContentView(R.layout.list_view_demo)
        val adapter = FruitAdapter(
            this,
            R.layout.list_item_demo,
            data
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val fruit = data[position]
            Toast.makeText(this, fruit, Toast.LENGTH_SHORT).show()
        }
    }
}