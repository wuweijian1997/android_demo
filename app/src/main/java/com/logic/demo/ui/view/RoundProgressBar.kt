package com.logic.demo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.logic.demo.R
import kotlin.math.min

class RoundProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius: Int = 0
    private var color: Int = 0
    private var lineWidth: Int = 0
    private var textSize: Int = 0
    private var progress: Int = 0
    private lateinit var progressCircularRectF: RectF
    private val paint = Paint()

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar, 0, 0).apply {
            radius = getDimension(R.styleable.RoundProgressBar_radius, dp2px(30)).toInt()
            color = getColor(R.styleable.RoundProgressBar_color, 0xffff0000.toInt())
            lineWidth = getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3)).toInt()
            textSize =
                getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16)).toInt()
            progress = getInt(R.styleable.RoundProgressBar_android_progress, 0)
            recycle()
        }

        paint.isAntiAlias = true
        paint.color = color
    }

    private fun dp2px(dpVal: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal.toFloat(),
            resources.displayMetrics
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            val needWidth = measuredWidth() + paddingLeft + paddingRight
            if (widthMode == MeasureSpec.AT_MOST) {
                min(needWidth, widthSize)
            } else {
                needWidth
            }
        }

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            val needHeight = measuredHeight() + paddingTop + paddingBottom
            if (heightMode == MeasureSpec.AT_MOST) {
                min(needHeight, heightSize)
            } else {
                needHeight
            }
        }
        width = min(width, height)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        progressCircularRectF = RectF(0F, 0F, w - paddingLeft * 2f, h - paddingTop * 2f)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    fun getProgress(): Int = progress

    override fun onDraw(canvas: Canvas?) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = lineWidth / 4f

        canvas?.drawCircle(
            width / 2f,
            height / 2f,
            width / 2f - paddingLeft - paint.strokeWidth / 2,
            paint
        )

        paint.strokeWidth = lineWidth.toFloat()
        canvas?.save()
        canvas?.translate(paddingLeft.toFloat(), paddingRight.toFloat())
        val angle: Float = progress / 100f * 360
        canvas?.drawArc(progressCircularRectF, 0f, angle, false, paint)
        canvas?.restore()
        val text = "$progress %"
        paint.strokeWidth = 0f
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = textSize.toFloat()
        paint.style = Paint.Style.FILL
        //获取文字宽高
        val bound: Rect = Rect()
        paint.getTextBounds(text, 0, text.length, bound)

        val textHeight = bound.height().toFloat() - paint.descent() / 2
        canvas?.drawText(text, 0, text.length, width /2f, height /2 + textHeight / 2, paint)
    }

    private val INSTANCE = "instance"
    private val KEY_PROGRESS = "key_progress"

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putInt(KEY_PROGRESS, progress)
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            val bundle: Bundle = state
            val parcelable: Parcelable? = bundle.getParcelable<Parcelable>(INSTANCE)
            super.onRestoreInstanceState(parcelable)
            progress = bundle.getInt(KEY_PROGRESS)
            return
        }
        super.onRestoreInstanceState(state)
    }

    private fun measuredWidth(): Int {
        return radius * 2
    }

    private fun measuredHeight(): Int {
        return radius * 2
    }
}