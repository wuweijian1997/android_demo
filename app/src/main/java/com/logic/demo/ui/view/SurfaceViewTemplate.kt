package com.logic.demo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

class SurfaceViewTemplate @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {

    private lateinit var mThread: Thread
    private var isRunning = true
    private lateinit var paint: Paint
    private var mMaxRadius: Int = 0
    private var mMinRadius: Int = 0
    private var mRadius: Int = 0
    private var mFlag: Int = 1

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                mThread = Thread {
                    while (isRunning) {
                        drawSelf()
                    }
                }
                mThread.start()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                isRunning = false
            }
        })
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            isFocusable = true
            isFocusableInTouchMode = true
            keepScreenOn = true
        }
        initPaint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mMaxRadius = w.coerceAtMost(h) / 2 - 20
        mMinRadius = 30
        mRadius = 30
    }

    private fun initPaint() {
        paint = Paint().apply {
            isDither = true
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = 6f
            color = Color.GREEN
        }
    }

    private fun drawSelf() {
        val canvas = holder.lockCanvas()
        if (canvas != null) {
            drawCircle(canvas)
        }
        holder.unlockCanvasAndPost(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        canvas.save()
        canvas.translate(width /2f, height/2f)
        canvas.drawCircle(0f, 0f, mRadius.toFloat(), paint)
        canvas.restore()
        if (mRadius >= mMaxRadius) {
            mFlag = -1
        } else if (mRadius <= mMinRadius) {
            mFlag = 1
        }
        mRadius += mFlag * 2
    }
}