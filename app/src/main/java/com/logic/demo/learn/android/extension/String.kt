package com.logic.demo.learn.android.extension

import com.logic.demo.MainApplication
import android.widget.Toast

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MainApplication.context, this, duration).show()
}