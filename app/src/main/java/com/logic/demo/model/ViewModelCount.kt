package com.logic.demo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelCount: ViewModel() {
    val count = MutableLiveData(0)
}