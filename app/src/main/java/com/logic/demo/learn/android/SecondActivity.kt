package com.logic.demo.learn.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.logic.demo.R
import kotlinx.android.synthetic.main.second_layout.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        Log.d("SecondActivity", "Task id is $taskId")

        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")

        button2.setOnClickListener {
            /*   val intent = Intent()
               intent.putExtra("data_return", "Hello FirstActivity")
               setResult(Activity.RESULT_OK, intent)
               finish()*/
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    /// 暴露自己的入参
    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data1)
            context.startActivity(intent)

        }
    }
}