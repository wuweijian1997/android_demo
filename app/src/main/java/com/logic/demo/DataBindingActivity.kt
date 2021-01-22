package com.logic.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModelProvider
import com.logic.demo.databinding.ActivityDataBindingBinding
import com.logic.demo.model.ObservableCount
import com.logic.demo.model.ViewModelCount

class DataBindingActivity : AppCompatActivity() {
    private val count = ObservableCount(ObservableInt(0))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_data_binding
        )
        binding.observableCount = count
        binding.addCount.setOnClickListener {
            count.count.set(count.count.get() + 1)
        }

        val viewModelCount = ViewModelProvider(this)[ViewModelCount::class.java]
        binding.viewModelCount = viewModelCount
        binding.lifecycleOwner = this
        binding.viewModelBtn.setOnClickListener {
            viewModelCount.count.value = (viewModelCount.count.value ?: 0) + 1
        }
    }
}