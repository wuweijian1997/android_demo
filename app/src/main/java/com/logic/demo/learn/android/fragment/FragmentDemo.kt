package com.logic.demo.learn.android.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.logic.demo.R
import kotlinx.android.synthetic.main.left_fragment.*

class FragmentDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_demo)
        /*fragmentButton.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())*/
    }

 /*   private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager;
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        ///实现返回栈
        transaction.addToBackStack(null)
        transaction.commit()
    }*/
}