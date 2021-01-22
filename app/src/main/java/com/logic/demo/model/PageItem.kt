package com.logic.demo.model

data class PageItem<T>(val title: String, val pageClass: Class<out T>)