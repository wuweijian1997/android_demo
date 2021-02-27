package com.logic.demo.ui.view.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas

abstract class DrawablePart(context: Context, width: Int, height: Int, bitmap: Bitmap) {

    abstract fun draw(canvas: Canvas)
}