package com.logic.demo.learn.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.logic.demo.R
import com.logic.demo.learn.android.extension.showToast

class RecyclerViewAdapter(private val fruitList: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    ///用于创建ViewHolder实例.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_demo, parent, false)
        ///横向滚动
//            .inflate(R.layout.list_item_horizontal, parent, false)

        ///点击事件
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            "You clicked view: $fruit".showToast()
        }
        viewHolder.fruitName.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            "You clicked fruit name: $fruit".showToast()
        }
        return viewHolder
    }

    ///告诉RecyclerView一共有多少子项
    override fun getItemCount() = fruitList.size

//    用于对RecyclerView子项的数据进行赋值.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position];
        holder.fruitName.text = fruit;
    }
}