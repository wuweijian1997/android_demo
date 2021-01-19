package com.logic.demo.learn.android.demo.broadcast

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.logic.demo.R
import com.logic.demo.learn.android.extension.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            if(account == "admin" && password == "123456") {
                val intent = Intent(this, BroadcastMainActivity::class.java)
                startActivity(intent)
            } else {
                "account or password is invalid".showToast()
            }
        }
    }
}