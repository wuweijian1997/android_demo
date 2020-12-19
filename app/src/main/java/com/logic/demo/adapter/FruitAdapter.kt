package com.logic.demo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.logic.demo.R

class FruitAdapter(activity: Activity, private val resourceId: Int, data: List<String>) :
    ArrayAdapter<String>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitName: TextView = view.findViewById(R.id.fruitName)

            viewHolder = ViewHolder(fruitName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val fruit = getItem(position)
        if (fruit != null) {
            viewHolder.fruitName.text = fruit
        }
        return view
    }

    inner class ViewHolder(val fruitName: TextView)
}