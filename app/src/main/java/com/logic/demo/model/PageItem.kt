package com.logic.demo.model

import android.graphics.Color
import java.util.*

data class PageItem<T>(val title: String, val pageClass: Class<out T>)