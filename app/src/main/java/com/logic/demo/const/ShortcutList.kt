package com.logic.demo.const

import android.content.Intent
import com.logic.demo.R
import com.logic.demo.learn.android.demo.webview.WebviewActivity
import com.logic.demo.model.Shortcut

val shortcutList = listOf<Shortcut>(
    Shortcut("apple", "apple", "apple", R.drawable.apple, WebviewActivity::class.java),
    Shortcut("banana", "banana", "banana", R.drawable.banana, WebviewActivity::class.java),
    Shortcut("cherry", "cherry", "cherry", R.drawable.cherry, WebviewActivity::class.java),
    Shortcut("grape", "grape", "grape", R.drawable.grape, WebviewActivity::class.java)
)