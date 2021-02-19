package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.logic.demo.databinding.ActivityHandlerBinding
import java.lang.ref.WeakReference


class HandlerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHandlerBinding

    companion object {
        const val COUNTDOWN_TIME_CODE: Int = 1
        const val MAX_CODE:Int = 10
        const val DELAY_TIME: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handle: Handler = CountdownTimeHandler(this)
        val message = Message.obtain()
        message.what = COUNTDOWN_TIME_CODE
        message.arg1 = MAX_CODE
        handle.sendMessageDelayed(message, DELAY_TIME)
    }

    class CountdownTimeHandler(activity: HandlerActivity) : Handler() {
        private val mWeakReference: WeakReference<HandlerActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            if (msg.what == COUNTDOWN_TIME_CODE) {
                val binding = mWeakReference.get()!!.binding
                val value = msg.arg1 - 1
                binding.textView.text = value.toString()
                if (value > 0) {
                    val message = Message.obtain()
                    message.what = COUNTDOWN_TIME_CODE
                    message.arg1 = value
                    sendMessageDelayed(message, DELAY_TIME)
                }
            }
        }
    }
}