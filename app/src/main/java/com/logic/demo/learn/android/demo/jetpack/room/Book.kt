package com.logic.demo.learn.android.demo.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var name: String, var pages: Int, val author: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}