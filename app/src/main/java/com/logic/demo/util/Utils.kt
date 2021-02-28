package com.logic.demo.util

import android.content.Context
import android.util.TypedValue

object Utils {
    fun dp2px(context: Context, dpVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal,
            context.resources.displayMetrics
        )
    }
}