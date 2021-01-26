package com.logic.demo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> = MutableLiveData()

    //
    private val letterInput = MutableLiveData<String>()

    val uppercaseLetter = Transformations.map(letterInput) { address ->
        address.toUpperCase()
    }

    fun setInput(address: String) {
        letterInput.value = address
    }

    private val searchText = MutableLiveData<String>()

  /*  val fetchSearch = Transformations.switchMap(searchText) {

    }*/

    fun searchInput(search: String) {
        searchText.value = search
    }
}