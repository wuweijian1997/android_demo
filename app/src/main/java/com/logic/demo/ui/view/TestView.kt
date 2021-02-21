package com.logic.demo.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.logic.demo.R
import kotlin.math.min

class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val booleanTest: Boolean
    var stringTest: String
    val enumTest: Int
    val dimensionTest: Float
    val integerTest: Int
    private val paint = Paint()

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TestView, 0, 0).apply {
            booleanTest = getBoolean(R.styleable.TestView_test_boolean, false)
            stringTest = getString(R.styleable.TestView_test_string).toString()
            enumTest = getInt(R.styleable.TestView_test_enum, 0)
            dimensionTest = getDimension(R.styleable.TestView_test_dimension, 0f)
            integerTest = getInteger(R.styleable.TestView_test_integer, 0)
        }

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 6f
        paint.color = 0xFF000000.toInt()
        paint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val width = if (widthMode == MeasureSpec.EXACTLY) {
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

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(width / 2f, height / 2f, width / 2f - paint.strokeWidth / 2, paint)
        paint.strokeWidth = 1f
        canvas?.drawLine(0f, height / 2f, width / 1f, height / 2f, paint)
        canvas?.drawLine(width / 2f, 0f, width / 2f, height / 1f, paint)

        paint.textSize = 64f
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
        canvas?.drawText(stringTest, 0, stringTest.length, 0f, height.toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        stringTest = "8888"
        invalidate()
        return true
    }

    private val INSTANCE = "instance"
    private val KEY_TEXT = "key_text"

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putString(KEY_TEXT, stringTest)
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if(state is Bundle) {
            val bundle: Bundle = state
            val parcelable: Parcelable? =  bundle.getParcelable<Parcelable>(INSTANCE)
            super.onRestoreInstanceState(parcelable)
            stringTest = bundle.getString(KEY_TEXT).toString()
            return
        }
        super.onRestoreInstanceState(state)
    }

    private fun measuredWidth(): Int {
        return 0
    }

    private fun measuredHeight(): Int {
        return 0
    }
}