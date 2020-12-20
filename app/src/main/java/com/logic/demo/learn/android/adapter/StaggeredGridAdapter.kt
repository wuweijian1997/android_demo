package com.logic.demo.learn.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.logic.demo.R

class StaggeredGridAdapter(private val fruitList: List<String>) :
    RecyclerView.Adapter<StaggeredGridAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    ///用于创建ViewHolder实例.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_staggered_grid, parent, false)
        return ViewHolder(view)
    }

    ///告诉RecyclerView一共有多少子项
    override fun getItemCount() = fruitList.size

//    用于对RecyclerView子项的数据进行赋值.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position];
        holder.fruitName.text = fruit;
    }
}