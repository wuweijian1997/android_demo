package com.logic.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.logic.demo.R

data class ViewPageModel(val text: String, val color: Int)

class ViewPageAdapter() : RecyclerView.Adapter<ViewPageAdapter.ViewPageHolder>() {
    inner class ViewPageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewPageText: TextView = view.findViewById(R.id.viewPageText)
    }

    private val layoutIds = listOf<ViewPageModel>(
        ViewPageModel("First", R.color.colorAccent),
        ViewPageModel("Second", R.color.colorPrimary),
        ViewPageModel("Third", R.color.colorPrimaryDark)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_page, parent, false)
        return ViewPageHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPageHolder, position: Int) {
        val model = layoutIds[position]
        holder.viewPageText.text = model.text
        holder.viewPageText.setBackgroundResource(model.color)
    }

    override fun getItemCount(): Int {
        return layoutIds.size
    }

}