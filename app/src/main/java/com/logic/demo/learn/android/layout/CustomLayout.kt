package com.logic.demo.learn.android.layout

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.logic.demo.R
import kotlinx.android.synthetic.main.custom_layout_demo.view.*

class CustomLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.custom_layout_demo, this)
        customBack.setOnClickListener{
            val activity = context as Activity
            activity.finish()
        }
        customEdit.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
}