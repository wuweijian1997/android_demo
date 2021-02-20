package com.logic.demo.ui.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.logic.demo.R

class TestView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    private val mContext: Context = context

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TestView, 0 ,0).apply {
            val booleanTest = getBoolean(R.styleable.TestView_test_boolean, false)
            val stringTest = getString(R.styleable.TestView_test_string)
            val enumTest = getInt(R.styleable.TestView_test_enum, 0)
            val dimensionTest = getDimension(R.styleable.TestView_test_dimension, 0f)
            val integerTest = getInteger(R.styleable.TestView_test_integer, 0)
        }
    }
}