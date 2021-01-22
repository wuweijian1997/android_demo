package com.logic.demo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import com.logic.demo.BR

data class ObservableCount( var count: ObservableInt)
/*
data class ObservableCount(var count: Int) : BaseObservable(){
    fun plusOne() {
        count++
        notifyPropertyChanged(BR.observableCount)
    }
}*/
