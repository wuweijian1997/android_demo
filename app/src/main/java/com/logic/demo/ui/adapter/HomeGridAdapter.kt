package com.logic.demo.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.logic.demo.R
import com.logic.demo.model.PageItem
import java.util.*

class HomeGridAdapter(private val pageList: List<PageItem<AppCompatActivity>>) :
    RecyclerView.Adapter<HomeGridAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homePageItem: TextView = view.findViewById(R.id.homePageItemText)
        val homePageCard: MaterialCardView = view.findViewById(R.id.homePageCard)
    }
    private val random: Random = Random()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_page_item, parent, false)
        ///点击事件
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val pageItem = pageList[position]
            val intent = Intent(parent.context, pageItem.pageClass)
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pageItem = pageList[position]
        holder.homePageItem.text = pageItem.title
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val randomColor = Color.valueOf(random.nextFloat() * 150, random.nextFloat() * 150, random.nextFloat() * 150)
            holder.homePageCard.setBackgroundColor(randomColor.toArgb())
        }
    }

    override fun getItemCount() = pageList.size
}