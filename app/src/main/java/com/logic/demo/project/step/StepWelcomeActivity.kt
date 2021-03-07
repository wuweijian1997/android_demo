package com.logic.demo.project.step

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R

class StepWelcomeActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private val jumpRunnable: Runnable = Runnable {
        val intent: Intent = Intent(this, StepActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_welcome)
        handler = Handler()

    }

    
}