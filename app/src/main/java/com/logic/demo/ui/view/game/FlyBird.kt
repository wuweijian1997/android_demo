package com.logic.demo.ui.view.game

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.annotation.DrawableRes
import com.logic.demo.R

class FlyBird @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {

    private lateinit var mThread: Thread
    private var isRunning = true

    private lateinit var mDestRect: RectF

    // res
    private lateinit var mBg: Bitmap
    private lateinit var mBirdBm: Bitmap
    private lateinit var mFloorBm: Bitmap

    private lateinit var mFloor: Floor
    private lateinit var mBird: Bird


    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                mThread = Thread {
                    while (isRunning) {
                        val start = System.currentTimeMillis()
                        drawSelf()
                        val end = System.currentTimeMillis()
                        if (end - start < 50) {
                            try {
                                Thread.sleep(50 - (end - start))
                            } catch (e: Exception) {
                            }
                        }
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

        initRes()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mDestRect = RectF(0f, 0f, w.toFloat(), h.toFloat())
        mFloor = Floor(context, w, h,mFloorBm)
        mBird = Bird(context, w, h,mBirdBm)
    }

    private fun initRes() {
        mBg = loadBitmapByResId(R.drawable.bg1)
        mBirdBm = loadBitmapByResId(R.drawable.b1)
        mFloorBm = loadBitmapByResId(R.drawable.floor_bg)
    }

    private fun loadBitmapByResId(@DrawableRes resId: Int): Bitmap {
        return BitmapFactory.decodeResource(resources, resId)
    }

    private fun drawSelf() {
        val canvas = holder.lockCanvas()
        if (canvas != null) {
            drawBg(canvas)
            mFloor.draw(canvas)
            mBird.draw(canvas)
        }
        holder.unlockCanvasAndPost(canvas)
    }

    private fun drawBg(canvas: Canvas?) {
        canvas?.drawBitmap(mBg, null, mDestRect, null)
    }
}