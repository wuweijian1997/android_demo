package com.logic.demo.ui.page

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R
import com.logic.demo.databinding.ActivityDragDropBinding
import com.logic.demo.learn.android.extension.showToast

class DragDropActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDragDropBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDragDropBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.cherry)
        imageView.setOnDragListener { v, event ->
            Log.d("Drag", event.x.toString())
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    ///确定此视图是否可以接受拖动的数据
                    Log.d("mimeType",
                        event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN).toString()
                    )
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        (v as? ImageView)?.setColorFilter(Color.BLUE)
                        v.invalidate()
                        //返回true表示View可以接受拖动的数据。
                        true
                    } else {
                        //返回false。在当前的拖放操作期间，此视图将
                        //发送ACTION_DRAG_ENDED之前，不会再次接收事件。
                        false
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    (v as? ImageView)?.setColorFilter(Color.GREEN)
                    v.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val item: ClipData.Item = event.clipData.getItemAt(0)
                    val dragData = item.text
                    Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_SHORT).show()
                    (v as? ImageView)?.clearColorFilter()
                    v.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    (v as? ImageView)?.clearColorFilter()
                    v.invalidate()
                    when (event.result) {
                        true -> "The drop was handled."
                        else -> "The drop didn't work."
                    }.showToast()
                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.dragDrop.addView(imageView)
    }
}