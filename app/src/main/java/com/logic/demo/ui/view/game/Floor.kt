package com.logic.demo.ui.view.game

import android.content.Context
import android.graphics.*
import android.util.Log

class Floor(val context: Context, val width: Int, val height: Int, private val bitmap: Bitmap) :
    DrawablePart(context, width, height, bitmap) {
    private val radioYPos = 4 / 5F
    private var x: Int = 0
    private var y: Int = (height * radioYPos).toInt()

    /*
    * Shader.TileMode.REPEAT: 重复
    * Shader.TileMode.CLAMP: 拉伸
    * */
    private val mBitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        isDither = true
    }

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.translate(x.toFloat(), y.toFloat())
        paint.shader = mBitmapShader
        canvas.drawRect(x.toFloat(), 0f, -x + width.toFloat(), height - y.toFloat(), paint)
        canvas.restore()
        paint.shader = null
    }

    fun getX() = x
    fun setX(newX: Int) {
        this.x = newX
        if(-x > width) {
            this.x %= width
        }
    }
}