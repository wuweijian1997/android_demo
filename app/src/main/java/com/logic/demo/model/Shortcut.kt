package com.logic.demo.model

import androidx.appcompat.app.AppCompatActivity

data class Shortcut(
    val id: String,
    val shortLabel: String,
    val longLabel: String,
    val icon: Int,
    val clazz: Class<out AppCompatActivity>
)