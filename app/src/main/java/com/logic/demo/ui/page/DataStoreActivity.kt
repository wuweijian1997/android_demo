package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.liveData
import com.logic.demo.R
import com.logic.demo.databinding.ActivityDataStoreBinding
import com.logic.demo.logic.Repository
import com.logic.demo.model.CountPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException

class DataStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val count = Repository.getCount().value
        Log.d("Activity count", "$count")
        binding.count.text = count.toString()
        binding.plusOne.setOnClickListener {
            val nextCount: Int = binding.count.text.toString().toInt() + 1
            Repository.updateCount(nextCount)
            binding.count.text = nextCount.toString()
        }
    }
}

