package com.logic.demo.util

import android.graphics.Color
import android.os.Build
import java.util.*

object ColorUtil {
    private val random: Random = Random()

    fun randomColor(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Color.valueOf(
                random.nextFloat() * 150,
                random.nextFloat() * 150,
                random.nextFloat() * 150
            ).toArgb()
        }
        return Color.GREEN
    }
}