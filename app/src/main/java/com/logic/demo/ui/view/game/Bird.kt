package com.logic.demo.ui.view.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.util.Log
import com.logic.demo.util.Utils

class Bird(context: Context, width: Int, height: Int, private val bitmap: Bitmap) :
    DrawablePart(context, width, height, bitmap) {
    private val radioYPos = 1 / 2f
    private val widthBird: Float = 34f
    private val heightBird: Float = 24f
    private val mWidth: Int = Utils.dp2px(context, widthBird).toInt()
    private var mHeight: Int = Utils.dp2px(context, heightBird).toInt()
    private val mRect: RectF = RectF()
    private val x: Float = width / 2 - mWidth / 2f
    private var y: Float = radioYPos * height

    override fun draw(canvas: Canvas) {
        mRect.set(x, y, x + mWidth, y + mHeight)
        canvas.drawBitmap(bitmap, null, mRect, null)
    }

    fun getY() = y
    fun setY(newY: Float) {
        this.y = newY
    }
}