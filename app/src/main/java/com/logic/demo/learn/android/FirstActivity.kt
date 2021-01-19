package com.logic.demo.learn.android

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.logic.demo.R
import com.logic.demo.learn.android.extension.showToast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity", "Task id is $taskId")
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            //显式Intent
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            //隐式Intent
            /*val intent = Intent("com.logic.activitytest.ACTION_START")
            intent.addCategory("com.logic.activitytest.MY_CATEGORY")
            startActivity(intent)
            */
            // 打开网页
         /*   val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)*/
            /*参数传递*/
            /*val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data", "Hello World")
            startActivity(intent)*/
            /*参数返回*/
            /*val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is :$returnedData")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> "You clicked Add".showToast()
            R.id.remove_item -> "You clicked Remove".showToast()
        }
        return true
    }
}