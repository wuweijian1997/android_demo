package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.logic.demo.databinding.ActivityLiveDataBinding
import com.logic.demo.ui.viewmodel.NameViewModel

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private val model: NameViewModel by lazy { ViewModelProvider(this)[NameViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameObserver = Observer<String> {
            binding.nameTextView.text = it
        }
        model.currentName.observe(this, nameObserver)
        binding.button.setOnClickListener {
            model.currentName.value = "Hello World"
        }

        binding.input.addTextChangedListener {
            model.setInput(it.toString())
        }

        model.uppercaseLetter.observe(this, Observer {
            binding.inputText.text = it
        })

        binding.search.addTextChangedListener {
            model.searchInput(it.toString())
        }

        model.fetchSearch.observe(this, Observer {
            val result = it.getOrNull()
            binding.searchText.text = result
        })
    }
}