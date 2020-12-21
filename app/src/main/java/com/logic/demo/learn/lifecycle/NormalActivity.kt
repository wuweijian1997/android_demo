package com.logic.demo.learn.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logic.demo.R

class NormalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.normal_layout)
    }
}