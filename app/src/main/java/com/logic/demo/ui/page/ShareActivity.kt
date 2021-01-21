package com.logic.demo.ui.page

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.databinding.ActivityShareBinding
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class ShareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShareBinding

    private val handle: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    val bmp = msg.obj as Bitmap
                    binding.shareImage.setImageBitmap(bmp)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")
        binding.shareText.text = title
        Log.d("title: $title", "img: $img")
        Thread {
            val bmp = getURLImage(img)
            val msg = Message()
            msg.what = 0
            msg.obj = bmp
            handle.sendMessage(msg)
        }.start()
        binding.shareSend.setOnClickListener {
            val intent = Intent()
            intent.putExtra("result", true)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    //加载图片
    private fun getURLImage(img: String?): Bitmap? {
        var bmp: Bitmap? = null
        try {
            val url = URL(img)
            // 获得连接
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 6000 //设置超时
            conn.doInput = true
            conn.useCaches = false //不缓存
            conn.connect()
            val inputStream: InputStream = conn.inputStream //获得图片的数据流
            bmp = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bmp
    }
}