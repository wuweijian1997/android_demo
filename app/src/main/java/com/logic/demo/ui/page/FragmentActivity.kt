package com.logic.demo.ui.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R
import com.logic.demo.databinding.ActivityFragmentBinding
import com.logic.demo.ui.fragment.DynamicLoadFragment

class FragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dynamicFragment.setOnClickListener {
            /// static load fragment.
            supportFragmentManager.beginTransaction().add(R.id.dynamicFragmentContainer, DynamicLoadFragment()).commit()
        }
    }
}